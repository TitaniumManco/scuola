const numeri = [
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
  11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
  21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
  31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
  41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
  51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
  61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
  71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
  81, 82, 83, 84, 85, 86, 87, 88, 89, 90
]

let realUsciti = []
let fakeUsciti = []

createTable()
usedNumbers()

let generare = document.getElementById("generate")

generare.addEventListener("click", generateNum)

function createTable(){
    let html='<table>'

    for(let i=0; i<90; i+=10){
        html+='<tr>'
        for(let z=0; z<10; z++){
            if(realUsciti.includes(numeri[i+z]))
            {
                html+='<td class="colorato">'+numeri[i+z]+'</td>'
            }
            else{
                html+='<td>'+numeri[i+z]+'</td>'
            }
        }
        html+='</tr>'
    }

    html+='</table>'
    document.getElementById("cards").innerHTML=html
}

function usedNumbers(){
    
    let html='<table>'

    for(let i=0; i<fakeUsciti.length; i++){
        html+='<tr><td>'+fakeUsciti[fakeUsciti.length-i-1]+'</td></tr>'
    }

    html+='</table>'
    document.getElementById("numbers").innerHTML=html


    createTable()
}


function generateNum(){
    if(realUsciti.length==numeri.length){
        alert("Tutti i numeri sono stati estratti!")
    }
    else{
        let numero = Math.floor(Math.random() * 90) + 1
        
        while(realUsciti.includes(numero)){
            numero = Math.floor(Math.random() * 90) + 1
        }

        if(fakeUsciti.length >= 5){
            fakeUsciti.shift()
        }
        fakeUsciti.push(numero)

        realUsciti.push(numero)

        usedNumbers()
    }
}