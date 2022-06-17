// Asynkron proces der venter (await) til listen er læst inden den bliver vist

(async () =>
{
    const ninjoServiceTableBody = document.getElementById("ninjoServiceTableBody");
    const sognListe = await getAllNinjoServices();

    sognListe.forEach(sogn => {
        ninjoServiceTableBody.innerHTML +=
            `
                <tr>
                    <td>${sogn.sognekode}</td>
                    <td>${sogn.navn}</td>
                </tr>
            `
    })

})()

// Vis alle sogne / Show all sogne
async function getAllNinjoServices()
{
    const sognAPI = "/services";
    return await fetch(sognAPI).then(response => response.json());
}

