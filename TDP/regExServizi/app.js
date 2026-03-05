function checkForm(){

    let nome = document.getElementById("usernameInput").value.toLowerCase()
    let email = document.getElementById("emailInput").value.toLowerCase()
    let telefono = document.getElementById("telefonoInput").value
    let budget = document.getElementById("budgetInput").value

    let html = document.getElementById("HTML").checked
    let php = document.getElementById("PHP").checked
    let asp = document.getElementById("ASP").checked
    let java = document.getElementById("Java").checked
    let cpp = document.getElementById("C++").checked

    let nomeRegex = /^[a-z\s]{3,}$/i
    let emailRegex = /^[a-z0-9]+@[a-z]+\.[a-z]{2,}$/i
    let telefonoRegex = /^[0-9]{10}$/
    let budgetRegex = /^[0-9]+$/


    if(!nomeRegex.test(nome)) {
        alert("Il nome deve essere almeno 3 caratteri e contenere solo lettere!")
        return
    }

    if(!emailRegex.test(email)) {
        alert("Email non valida!")
        return
    }

    if(!telefonoRegex.test(telefono)) {
        alert("Numero di telefono non valido!")
        return
    }

    if(!budgetRegex.test(budget)) {
        alert("Il budget deve essere positivo")
        return
    }

    if(!html && !php && !asp && !java && !cpp) {
        alert("Seleziona almeno un servizio")
        return
    }
    
    summary(nome, email, telefono, budget, html, php, asp, java, cpp)
    return
}


function summary(nome, email, telefono, budget, html, php, asp, java, cpp){

    let costoTotale = 0
    let servizi = ""

    if(html) {
        costoTotale += 200
        servizi += "HTML (€200) "
    }
    if(php) {
        costoTotale += 250
        servizi += "PHP (€250) "
    }
    if(asp) {
        costoTotale += 300
        servizi += "ASP (€300) "
    }
    if(java) {
        costoTotale += 350
        servizi += "Java (€350) "
    }
    if(cpp) {
        costoTotale += 400
        servizi += "C++ (€400) "
    }

    let budgetOk = ""
    if (budget >= costoTotale) {
        budgetOk = "SÌ"
    } else {
        budgetOk = "NO"
    }

    let ris = "<h2> Riepilogo: </h2>"
    ris += "<p>Nome: " + nome + "</p>"
    ris += "<p>Email: " + email + "</p>"
    ris += "<p>Telefono: " + telefono + "</p>"
    ris += "<p>Budget: €" + budget + "</p>"
    ris += "<p>Servizi Scelti: " + servizi + "</p>"
    ris += "<p>Costo Totale: €" + costoTotale + "</p>"
    ris += "<p>Budget Sufficiente: " + budgetOk + "</p>"

    document.getElementById("risultato").innerHTML = ris
}