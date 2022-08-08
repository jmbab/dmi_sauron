// Asynkron proces der venter (await) til listen er læst inden den bliver vist

(async () =>
{
    const ninjoServiceTableBody = document.getElementById("ninjoServiceTableBody");
    const ninjoServiceList = await getAllNinjoServices();

    await createNinjoService();

    ninjoServiceList.forEach(ninjoServiceModel => {
        ninjoServiceTableBody.innerHTML +=
            `
                <tr>
                    <td>${ninjoServiceModel.servername}</td>
                    <td>${ninjoServiceModel.uptime}</td>
                    <td>${ninjoServiceModel.diskfree}</td>
                </tr>
            `
    })

})()

// Vis alle ninjo service / Show all ninjo services
async function getAllNinjoServices()
{
    const ninjoServiceAPI = "/*/json_received/127.0.0.1_serverinfo.json";
    return await fetch(ninjoServiceAPI)
        .then(response => {
        if (!response.ok) {
            throw new Error("HTTP error: " + response.status);
        }
        return response.json();
    })
        .catch(function () {
            this.dataError = true;
        })
}

// opret / create service
async function createNinjoService()
{
    const createNinjoServiceAPI= "../json_received/localhost_serverinfo.json";
    const postObject = {
        method:"POST",
        headers: {
            "Content-type": 'application/json',
        },
        body:JSON.stringify({
            "servername":document.getElementById("servername").value,
            "uptime":document.getElementById("uptime").value,
            "diskfree":document.getElementById("diskfree").value,
        })
    }

    return await fetch(createNinjoServiceAPI,postObject)
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            return response.json();
        })
    location.reload();
}


