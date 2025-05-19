import axios from 'axios'

const SERVER_ADDRESS = 'http://localhost:4567/api/'

export function getQualification(description) {
    return axios.get(SERVER_ADDRESS + 'qualifications/' + description).then((res) => JSON.parse(res.request.response))
}

export function getQualifications() {
    return axios.get(SERVER_ADDRESS + 'qualifications').then((res) => JSON.parse(res.request.response).sort((a, b) => a.description.localeCompare(b.description)))
}

export function createQualification(description) {
    return axios.post(SERVER_ADDRESS + 'qualifications/' + description, { description: description })
}

export function getProjects() {
    return axios.get(SERVER_ADDRESS + 'projects').then((res) => JSON.parse(res.request.response).sort((a, b) => a.name.localeCompare(b.name)))
}

export function createWorker(name, salary, qualifications) {
    const encoded = encodeURIComponent(name);
    return axios.post(
      `${SERVER_ADDRESS}workers/${encoded}`,
      { name, salary, qualifications }          // proper JSON object
    );
}
export function startProject(projectName) {
    return axios.put(`${SERVER_ADDRESS}start`, { name: projectName })
}

export function finishProject(projectName) {
    return axios.put(`${SERVER_ADDRESS}finish`, { name: projectName })
}

export function getWorkers() {
    return axios.get('http://localhost:4567/api/workers').then((res) => res.data); // use res.data, NOT res.request.response
}
export function getWorker(id) {
    return axios.get(SERVER_ADDRESS + 'workers/' + id).then((res) => JSON.parse(res.request.response))
}


export function assignWorker(workerName, projectName) {
    return axios.put(`${SERVER_ADDRESS}assign`, {
        worker: workerName,
        project: projectName,
    })
}

export function unassignWorker(workerName, projectName) {
    return axios.put(`${SERVER_ADDRESS}unassign`, {
        worker: workerName,
        project: projectName,
    })
}

export function createNewProject(projectName, projectQualifications, projectSize) {
    return axios.post(SERVER_ADDRESS + "projects/" + projectName, {
        name: projectName,
        qualifications: projectQualifications,
        size: projectSize
    })
}