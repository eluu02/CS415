import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { 
    getWorkers,
    getQualifications,
    createWorker 

} from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

// Renders a single worker entry; shows details when active
const Worker = (worker, active) => {
    return (
        <div>
            <div>{worker.name}</div>
            {active ? <WorkerBody worker={worker} /> : null}
        </div>
    )
}

// Renders expanded details for a worker
const WorkerBody = ({ worker }) => {
    return (
        <div style={grayContainerStyle}>
            <div>Salary: ${worker.salary}</div>
            <div>Workload: {worker.workload}</div>
            <div>Projects:</div>
            <ClickList list={worker.projects} styles={darkGrayContainerStyle} path="/projects" />
            <div>Qualifications:</div>
            <ClickList list={worker.qualifications} styles={darkGrayContainerStyle} path="/qualifications" />
        </div>
    )
}

// Main component to fetch and display all workers
const Workers = () => {
    const [workers, setWorkers]           = useState([])
    const [showModal, setShowModal]       = useState(false)   // NEW
    const [name, setName]                 = useState('')      // NEW
    const [salary, setSalary]             = useState('')      // NEW
    const [quals, setQuals]               = useState([])      // NEW (selected quals)
    const [allQuals, setAllQuals]         = useState([])      // NEW (list for select)
    const [error, setError]               = useState('')      // NEW
  
    /* fetch workers once ------------------------------------------------ */
    useEffect(() => { getWorkers().then(setWorkers) }, [])
  
    /* helper so we can refresh after creation --------------------------- */
    const refreshWorkers = () => getWorkers().then(setWorkers)
  
    /* show modal and load qualifications -------------------------------- */
    const openModal = () => {
      setError('')
      setName('')
      setSalary('')
      setQuals([])
      getQualifications().then(setAllQuals)
      setShowModal(true)
    }
  
    /* create‑worker submit --------------------------------------------- */
    const handleCreate = async () => {
      if (!name.trim() || !salary || quals.length === 0) {
        setError('Name, salary, and at least one qualification are required.');
        return;
      }
    
      try {
        await createWorker(name.trim(), Number(salary), quals);
        setShowModal(false);
        refreshWorkers();
      } catch (e) {
        if (e.response?.status === 400) {
          setError(e.response.data || 'Worker name already exists.');
        } else if (e.response?.status >= 500) {
          setError('Unable to create worker. Please try again.');
        } else {
          setError('Backend error: ' + (e.response?.data ?? 'see console'));
        }
      }
    };
  
    /* active dropdown id helper ---------------------------------------- */
    const active = LocationID('workers', workers, 'name')
  
    return (
      <div style={pageStyle}>
        <h1>All Company Workers</h1>
  
        {/* NEW – Create Worker button */}
        <button onClick={openModal} style={{ marginBottom: '1rem' }}>
          Create Worker
        </button>
  
        {/* existing expandable list */}
        <ClickList
          active={active}
          list={workers}
          item={Worker}
          path="/workers"
          id="name"
        />
  
        {/* NEW – modal markup (simple div overlay) */}
        {showModal && (
          <div
            className="modal"
            onClick={() => setShowModal(false)}
            style={{
              position: 'fixed',
              inset: 0,
              backgroundColor: 'rgba(0,0,0,0.4)',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              zIndex: 999
            }}
          >
            <div
              onClick={e => e.stopPropagation()}
              style={{ ...grayContainerStyle, width: '320px' }}
            >
              <h3>Create New Worker</h3>
  
              {error && <div style={{ color: 'red' }}>{error}</div>}
  
              <label>
                Name:
                <input
                  value={name}
                  onChange={e => setName(e.target.value)}
                  style={{ width: '100%' }}
                />
              </label>
              <br />
  
              <label>
                Salary:
                <input
                  type="number"
                  value={salary}
                  onChange={e => setSalary(e.target.value)}
                  style={{ width: '100%' }}
                />
              </label>
              <br />
  
              <label>Qualifications (Ctrl/Cmd‑click for multi‑select):</label>
              <select
                multiple
                size="5"
                value={quals}
                onChange={e =>
                  setQuals([...e.target.selectedOptions].map(o => o.value))
                }
                style={{ width: '100%' }}
              >
                {allQuals.map(q => (
                  <option key={q.description} value={q.description}>
                    {q.description}
                  </option>
                ))}
              </select>
              <br />
  
              <button onClick={handleCreate}>Confirm</button>
              <button onClick={() => setShowModal(false)}>Cancel</button>
            </div>
          </div>
        )}
      </div>
    )
  }
  
  export default Workers