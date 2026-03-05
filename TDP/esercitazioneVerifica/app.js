function checkForm(){

    let nome = document.getElementById("usernameInput").value
    let password = document.getElementById("passwordInput").value
    let numeroDiTelefono = document.getElementById("telefonoInput").value
    let url = document.getElementById("urlInput").value
    let ente = document.getElementById("enteInput").value
    let data = document.getElementById("dateInput").value

    let linux = document.getElementById("Linux").checked
    let windows = document.getElementById("Windows").checked
    let android = document.getElementById("Android").checked
    let database = document.getElementById("Database").checked

    let nomeRegex = /^[a-z\s']{3,}$/i
    let passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$/
    let numeroDiTelefonoRegex = /^\d{10}$/
    let urlRegex = /^https?:\/\/[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
    let enteRegex = /^[a-zA-Z0-9\s.,'&-]{3,}$/
    let dataRegex = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])$/


    if(!nomeRegex.test(nome)) {
        alert("Il nome deve essere almeno 3 caratteri e puo' contenere lettere, spazi e apostrofi!")
        return
    }

    if(!passwordRegex.test(password)) {
        alert("La password deve contenere almeno 8 caratteri e contenere almeno 1 numero e 1 lettera!")
        return
    }

    if(!numeroDiTelefonoRegex.test(numeroDiTelefono)) {
        alert("Numero di telefono non valido!")
        return
    }

    if(!urlRegex.test(url)) {
        alert("URL non valido")
        return
    }

    if(!enteRegex.test(ente)) {
        alert("Nome ente non valido")
        return
    }

    if(!linux && !windows && !android && !database) {
        alert("Seleziona almeno un servizio")
        return
    }
    
    if(!dataRegex.test(data)) {
        alert("Formato data non valido (YYYY-MM-DD)")
        return
    }

    summary(nome, password, numeroDiTelefono, url, ente, data, linux, windows, android, database)
    return
}


function summary(nome, password, numeroDiTelefono, url, ente, data, linux, windows, android, database){
    
    // Calcolo data del nuovo appuntamento
    let dataObj = new Date(data)
    dataObj.setDate(dataObj.getDate() + 7)
    if(dataObj.getDay() === 6) dataObj.setDate(dataObj.getDate() + 2)
    if(dataObj.getDay() === 0) dataObj.setDate(dataObj.getDate() + 1)
    let dataAppuntamento = dataObj.toISOString().split("T")[0]

    let servizi = ""

    if(linux) {
        servizi += "Linux "
    }
    if(windows) {
        servizi += "Windows "
    }
    if(android) {
        servizi += "Android "
    }
    if(database) {
        servizi += "Database"
    }


    let ris = "<h2> Riepilogo: </h2>"
    ris += "<p>Nome: " + nome + "</p>"
    ris += "<p>Password: " + "*".repeat(password.length) + "</p>"
    ris += "<p>Numero Di Telefono: " + numeroDiTelefono + "</p>"
    ris += "<p>URL: " + url + "</p>"
    ris += "<p>Ente: " + ente + "</p>"    
    ris += "<p>Servizi Scelti: " + servizi + "</p>"
    ris += "<p>Data Richiesta: " + data + "</p>"
    ris += "<p>Data Appuntamento: " + dataAppuntamento + "</p>"

    document.getElementById("risultato").innerHTML = ris
}