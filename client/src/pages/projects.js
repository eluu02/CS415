/* ------------------------------------------------------------------ */
/*  pages/projects.js                                                 */
/* ------------------------------------------------------------------ */
import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import Select from 'react-select'
import {
  getProjects,
  getWorkers,
  assignWorker,
  unassignWorker,
  startProject,
  finishProject,          // NEW
  createNewProject, 
  getQualifications
} from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

/* ------------------------------------------------------------------ */
/*  Single‑line renderer for <ClickList>                              */
/* ------------------------------------------------------------------ */
/*
const Project = (project, active) => (
  <div>
    <div>{project.name}</div>
    {active === true ? ProjectBody(project) : null}
  </div>
)
*/

/* ------------------------------------------------------------------ */
/*  Expanded project body                                             */
/* ------------------------------------------------------------------ */
const ProjectBody = ({ project, allWorkers, onRefresh }) => {
  const [showModal, setShowModal]       = useState(false)
  const [selectedWorkerName, setSelectedWorkerName] = useState('')
  const [error, setError]               = useState('')      // NEW banner
  const [isStarting, setIsStarting] = useState(false)    // NEW start flag

  /* workers that could be assigned */
  const available = allWorkers.filter(
    w => !project.workers.some(pw => pw === w.name)        // pw is a string
  )

  /* Assign‑worker confirm */
  const handleConfirm = async () => {
    try {
      await assignWorker(selectedWorkerName, project.name)
      setShowModal(false)
      onRefresh()
    } catch (e) {
      setError('Unable to assign worker. Please try again.')
    }
  }

  /* Unassign button */
  const handleUnassign = async (workerName) => {
    try {
      await unassignWorker(workerName, project.name)
      onRefresh()
    } catch (err) {
      console.error(err)
      setError('Failed to unassign worker. Please try again.')   // UPDATED
    }
  }

  /* === NEW === Finish‑project handler */
  const handleFinish = async () => {
    try {
      await finishProject(project.name)
      await onRefresh()            // status & worker list now updated
      alert('Project finished')    // simple toast; swap for real toast lib if desired
    } catch (e) {
      if (e.response?.status === 400) {
        setError('Only active projects can be finished.')
      } else if (e.response?.status === 404) {
        setError('Project does not exist.')
      } else {
        setError('Unable to finish project. Please try again.')
      }
    }
  }

  /* === NEW === Start‑project handler */
  const handleStart = async () => {
    setIsStarting(true)
    setError('')
    try {
      await startProject(project.name)
      await onRefresh()
    } catch (e) {
      if (e.response?.status === 400) {
        setError('Cannot start project unless all qualifications are met.')
      } else if (e.response?.status === 404) {
        setError('Project does not exist.')
      } else {
        setError('Unable to start project. Please try again.')
      }
    } finally {
      setIsStarting(false)
    }
  }

  return (
    <div style={grayContainerStyle}>
      <div><strong>Size:</strong> {project.size}</div>
      <div><strong>Status:</strong> {project.status}</div>

      {/* NEW error banner */}
      {error && <div style={{ color: 'red', marginBottom: 8 }}>{error}</div>}

      <div>
        <strong>Workers:</strong>
        <div style={darkGrayContainerStyle}>
          {project.workers.map(workerName => (
            <div key={workerName} style={{ display: 'flex', alignItems: 'center', marginBottom: 4 }}>
              <span style={{ flexGrow: 1 }}>{workerName}</span>
              <button
                onClick={e => {
                  e.stopPropagation()
                  handleUnassign(workerName)
                }}
              >
                Unassign
              </button>
            </div>
          ))}
        </div>
      </div>

      <div>
        <strong>Qualifications:</strong>
        <ul>
          {project.qualifications.map((q, i) => (
            <li key={i}>{q}</li>
          ))}
        </ul>
      </div>

      <div>
        <strong>Missing Qualifications:</strong>
        <ul>
          {project.missingQualifications.map((mq, i) => (
            <li key={i}>{mq}</li>
          ))}
        </ul>
      </div>

      {/* === NEW Start button, visible only when project is PLANNED */}
      {project.status === 'PLANNED' && (
        <button
          onClick={e => { e.stopPropagation(); handleStart() }}
          disabled={isStarting}
        >
          {isStarting ? 'Starting...' : 'Start Project'}
        </button>
      )}

      {/* === NEW Finish button, visible only when project is ACTIVE */}
      {project.status === 'ACTIVE' && (
        <button onClick={e => { e.stopPropagation(); handleFinish() }}>
          Finish Project
        </button>
      )}

      {/* existing Assign Worker flow */}
      <button
        onClick={e => {
          e.stopPropagation()
          setShowModal(true)
        }}
      >
        Assign Worker
      </button>

      {showModal && (
        <div
          className="modal"
          onClick={e => e.stopPropagation()}
          style={{ pageStyle }}
        >
          <h3>Assign a worker</h3>
          <select
            value={selectedWorkerName}
            onChange={e => setSelectedWorkerName(e.target.value)}
            onClick={e => e.stopPropagation()}
          >
            <option value="">— choose —</option>
            {available.map(w => (
              <option key={w.name} value={w.name}>
                {w.name}
              </option>
            ))}
          </select>

          <button
            disabled={!selectedWorkerName}
            onClick={e => {
              e.stopPropagation()
              handleConfirm()
            }}
          >
            Confirm
          </button>

          <button
            onClick={e => {
              e.stopPropagation()
              setShowModal(false)
            }}
          >
            Cancel
          </button>
        </div>
      )}
    </div>
  )
}

/* ------------------------------------------------------------------ */
/*  Create project                                                    */
/* ------------------------------------------------------------------ */
const projectSizeOptions = [
  { value: 'SMALL', label: 'Small' },
  { value: 'MEDIUM', label: 'Medium' },
  { value: 'BIG', label: 'Big' },
];

const CreateProjectModal = ({onClose, onCreate, qualifications}) => {
  const [projectName, setProjectName] = useState('');
  const [projectSize, setProjectSize] = useState('');
  const [selectedQualifications, setSelectedQualifications] = useState([]);
  
  const handleCreate = async (e) => {
    e.preventDefault()
    try {
      const qualificationsList = selectedQualifications.map(q => q.value);
      await createNewProject(projectName, qualificationsList, projectSize);
      onCreate();
      onClose();
    } catch (err) {
      console.error(err);
      alert(err);
    }
  };

  return (
    <div style={grayContainerStyle}>
      <h2>Create New Project</h2>
            <form onSubmit={handleCreate}>
                <div>
                    <label>Project Name:</label>
                    <input
                        type="text"
                        value={projectName}
                        onChange={(e) => setProjectName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Qualifications:</label>
                    <Select
                        options={qualifications}
                        isMulti 
                        value={selectedQualifications}
                        onChange={setSelectedQualifications}
                        placeholder="Select qualifications..."
                    />
                </div>
                <div>
                  <label>Project Size:</label>
                    <select
                      value={projectSize}
                      onChange={(e) => setProjectSize(e.target.value)}
                      required
                    >
                      <option value="">Select Size</option>
                      {projectSizeOptions.map((size) => (
                        <option key={size.value} value={size.value}>
                          {size.label}
                        </option>
                    ))}
                  </select>
                </div>
                <button type="submit">Create</button>
                <button type="button" onClick={onClose}>
                    Cancel
                </button>
            </form>
    </div>
  )
};

/* ------------------------------------------------------------------ */
/*  Page component                                                     */
/* ------------------------------------------------------------------ */
const Projects = () => {
  const [projects, setProjects]   = useState([])
  const [allWorkers, setAllWorkers] = useState([])
  const [showCreateModal, setShowCreateModal] = useState(false)
  const [qualifications, setQualifications] = useState([])

  /* initial fetch */
  useEffect(() => {
    getProjects().then(setProjects)
    getWorkers().then(setAllWorkers)
    getQualifications().then(data => {setQualifications(data);})
  }, [])

  const active  = LocationID('projects', projects, 'name')
  const refresh = () => getProjects().then(setProjects)
  
  const qualificationOptions = qualifications.map(q => ({
    value: q.description,
    label: q.description
  }))

  const renderProject = (project, isActive) => (
    <div>
      <div>{project.name}</div>
      {isActive && (
        <ProjectBody
          project={project}
          allWorkers={allWorkers}
          onRefresh={refresh}
        />
      )}
    </div>
  )

  return (
    <div style={pageStyle}>
      <button onClick={() => setShowCreateModal(true)}>Create New Project</button>
            {showCreateModal && (
                <CreateProjectModal
                    onClose={() => setShowCreateModal(false)}
                    onCreate={refresh}
                    qualifications={qualificationOptions}
                />
            )}
      <h1>This page displays a table containing all the projects.</h1>
      <ClickList
        active={active}
        list={projects}
        item={renderProject}
        path="/projects"
        id="name"
      />
    </div>
  )
}

export default Projects
