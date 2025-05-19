/**
 * Qualifications Page Component
 * 
 * Displays a list of qualifications, allows creation of new qualifications,
 * and lets users toggle to view qualification details and associated workers.
 */
import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getQualifications, createQualification } from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

const Qualification = (qualification, active) => {
    return (
        <div>
            <div>{qualification.description}</div>
            {active === true ? QualificationBody(qualification) : null}
        </div>
    )
}

const QualificationBody = (qualification) => {
    return (
        <div style={grayContainerStyle}>
            Workers: <ClickList list={qualification.workers} styles={darkGrayContainerStyle} path="/workers" />
        </div>
    )
}

const Qualifications = () => {
    const [qualifications, setQualifications] = useState([]) // holds list of qualifications
    const [newQualification, setNewQualification] = useState('') // input value for new qualification
    const [isSubmitting, setIsSubmitting] = useState(false) // tracks submission status

    // fetch initial qualifications on mount
    useEffect(() => { getQualifications().then(setQualifications) }, [])

    // submit handler for creating a new qualification
    const handleCreate = async (e) => {
        // prevent default form submission
        e.preventDefault()
        // indicate submission in progress
        setIsSubmitting(true)
        try {
            // call API to create qualification
            await createQualification(newQualification)
            setNewQualification('')
            // refresh qualifications list
            const updated = await getQualifications()
            setQualifications(updated)
        } catch (error) {
            // handle errors during creation
            console.error('Error creating qualification:', error)
        } finally {
            // reset submitting state
            setIsSubmitting(false)
        }
    }

    const active = LocationID('qualifications', qualifications, 'description')
    return (
        <div style={pageStyle}>
            <h1>
                This page displays a table containing all the qualifications.
            </h1>
            {/* input form for new qualification */}
            <form onSubmit={handleCreate} style={{ marginBottom: '1em' }}>
                <input
                    type="text"
                    value={newQualification}
                    onChange={e => setNewQualification(e.target.value)}
                    placeholder="Enter new qualification"
                    disabled={isSubmitting}
                />
                <button type="submit" disabled={isSubmitting || !newQualification.trim()}>
                    {isSubmitting ? 'Adding...' : 'Add Qualification'}
                </button>
            </form>
            {/* list of existing qualifications with toggleable details */}
            <ClickList active={active} list={qualifications} item={Qualification} path='/qualifications' id='description' />
        </div>
    )
}

export default Qualifications