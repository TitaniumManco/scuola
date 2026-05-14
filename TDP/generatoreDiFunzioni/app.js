google.charts.load('current', {'packages': ['corechart']})
google.charts.setOnLoadCallback(function() {})

let a, b, c, massimo, minimo, step, funzione

function aggiornaCampi() {
    funzione = document.getElementById('funzione').value

    document.getElementById('label-a').textContent = 'Parametro a:'
    document.getElementById('label-b').textContent = 'Parametro b:'

    if (funzione === 'retta') {
        document.getElementById('campo-c').style.display = 'none'
    } else {
        document.getElementById('campo-c').style.display = 'block'
    }
}

function getParametri() {
    funzione = document.getElementById('funzione').value
    a = parseFloat(document.getElementById('af').value)
    b = parseFloat(document.getElementById('bf').value)
    c = parseFloat(document.getElementById('cf').value)
    massimo = parseFloat(document.getElementById('max').value)
    minimo = parseFloat(document.getElementById('min').value)

    if(massimo < minimo){
        let placeHolder = minimo
        minimo = massimo
        massimo = placeHolder
    }

    step = parseFloat(document.getElementById('n').value)
}

function creaGrafico() {
    getParametri()

    let tabella = new google.visualization.DataTable()
    tabella.addColumn('number', 'x')
    tabella.addColumn('number', 'f(x)')

    let punti = (massimo - minimo) / step
    for (let i = 0; i <= punti; i++) {
        let x = minimo + i * step
        let y

        if (funzione === 'retta') {
            y = a * x + b
        } else {
            y = a * x * x + b * x + c
        }

        tabella.addRow([x, y])
    }

    let grafico = new google.visualization.LineChart(document.getElementById('divGrafico'))
    grafico.draw(tabella)
}


aggiornaCampi()