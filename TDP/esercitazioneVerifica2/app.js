function checkForm(){
    let error = false
    let nome = document.getElementById("nameInput").value
    let classe = document.getElementById("classInput").value

    let capitale = document.querySelector('input[name="france_capital"]:checked')
    let pianeta = document.querySelector('input[name="planet"]:checked')
    let matematica = document.querySelector('input[name="maths"]:checked')
    
    let datiError = document.getElementById("datiError")
    let capitaleError = document.getElementById("capitalError")
    let pianetaError = document.getElementById("planetError")
    let mateError = document.getElementById("mathsError")

    datiError.innerHTML = ""
    capitaleError.innerHTML = ""
    pianetaError.innerHTML = ""
    mateError.innerHTML = ""

    let nomeRegex = /^[a-zA-Z' ]{3,}$/i
    let classeRegex = /^[1-5][a-zA-Z]$/


    if(!nomeRegex.test(nome)) {
        datiError.innerHTML = "Nome non valido<br>"
        error = true
    }

    if(!classeRegex.test(classe)) {
        datiError.innerHTML += "Classe non valida<br>"
        error = true
    }

    if (capitale === null) {
        capitaleError.innerHTML = "Devi rispondere alla domanda!"
        error = true
    }

    if (pianeta === null) {
        pianetaError.innerHTML = "Devi rispondere alla domanda!"
        error = true
    }

    if (matematica === null) {
        mateError.innerHTML = "Devi rispondere alla domanda!"
        error = true
    }

    if(error === true){
        return
    }
    summary(nome, classe, capitale, pianeta, matematica)
    return
}


function summary(nome, classe, capitale, pianeta, matematica){
    let punti = 0
    let esito = ""

    if(capitale.value === "Parigi"){
        punti += 1.5
    }

    if(pianeta.value === "Marte"){
        punti += 1.5
    }

    if(matematica.value === "30"){
        punti += 1.5
    }

    if(punti <= 1.5){
        esito = "Risultato Scarso"
    }
    else if(punti == 3){
        esito = "Buon Risultato"
    }
    else{
        esito = "Ottimo Risultato"
    }

    let ris = "<h2> Riepilogo: </h2>"
    ris += "<p>Nome: " + nome + "</p>"
    ris += "<p>Classe: " + classe + "</p>"
    ris += "<p>Punteggio: " + punti + "</p>"
    ris += "<p>Valutazione: " + esito + "</p>"

    document.getElementById("risultato").innerHTML = ris
}