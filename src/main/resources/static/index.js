
// Asynkron proces der venter (await) til listen er læst inden den bliver vist
(async () =>
{
    const ninjoServiceTableBody = document.getElementById("ninjoServiceTableBody");
    const ninjoServiceList = await getAllNinjoServices();

    // await createNinjoService();
    document.getElementById("refreshall").addEventListener("click", function() {
        alert("Requesting status updates");
    });
    // const ninjoServiceAPI = 'json_received/cphninjo_01_serverinfo.json';

    ninjoServiceList.forEach(ninjoServiceModel => {
        ninjoServiceTableBody.innerHTML +=
            `
                <tr>
                    <!-- <td>${ninjoServiceModel.servername}</td>-->
                    <td>ninjocph_01</td>
                    <td>${ninjoServiceModel.uptime}</td>
                    <td>${ninjoServiceModel.diskfree}</td>
                </tr>
            `
    })

})()

// Vis alle ninjo service / Show all ninjo services
async function getAllNinjoServices()
{
    const ninjoServiceAPI = 'http://localhost:9090/ninjoservermodels/list';
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
/*async function createNinjoService()
{
    const ninjoServiceAPI = 'http://localhost:9090/ninjoservermodels/list';
    const postObject = {
        method:"POST",
        headers: {
            "Content-type": 'application/json',
            'Accept': 'application/json'
        },
        body:JSON.stringify({
            "servername":document.getElementById("servername").value, // kunne fx hentes i JSON filnavnet?
            "uptime":document.getElementById("uptime").value,
            "diskfree":document.getElementById("diskfree").value,
        })
    }

    return await fetch(ninjoServiceAPI, postObject)
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            return response.json();
        })
    document.location.reload();
}*/


