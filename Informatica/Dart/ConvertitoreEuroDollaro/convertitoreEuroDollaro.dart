import 'package:flutter/material.dart';

/// Semplice Widget che riscrive su un Text in valore di un TextField con evento
/// onPressed su Bottone.
/// Introduce l'uso di TextEditingController() che produce un oggetto relativo
/// da assegnare alla proprietà controller dei Widget TextField a cui ci si può
/// accedere al di fuori del widget stesso per ottenerne il testo editato,
/// es. nell'ascoltatore di un Button.

void main() {
  runApp(App());
}

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'TextField Controller',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomePageProva(title: 'Convertitore Euro Dollaro'),
    );
  }
}

class HomePageProva extends StatefulWidget {
  const HomePageProva({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _HomePageProvaState createState() => _HomePageProvaState();
}

class _HomePageProvaState extends State<HomePageProva> {
  String _msg = "";
  double _value = 0;
  int _conversionValue = 0;

  String _getMessage() {
    if (_conversionValue == 0) {
      return "Converti un valore nell'unità che desideri";
    } else if (_conversionValue == 1) {
      return '$_msg euro sono: $_value dollari';
    } else {
      return '$_msg dollari sono: $_value euro';
    }
  }

  // servirà per accedere dinamicamente (runtime) al widget TextField
  final _controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextField(
              // il widget sarà accessibile con reference assegnato _controller
              controller: _controller,
            ),
            const Divider(),
            ElevatedButton(
              child: const Text('Converti in dollari'),
              onPressed: () {
                /* viene richiamato il metodo build di questo State
                  dopo l'aggiornamento dell'attributo _msg col texto del
                  TexField referenziato da _controller
                  Così facendo viene ricostruita l'intero widget contenitore
                  _HomePageProvaState con l'effetto di aggiornare il valore del
                  widget Text qui sotto
                */
                setState(() {
                  _msg = _controller.text;
                  _value = double.parse(_controller.text) * 1.166;
                  _conversionValue = 1;
                });
              },
            ),
            ElevatedButton(
              child: const Text('Converti in euro'),
              onPressed: () {
                /* viene richiamato il metodo build di questo State
                  dopo l'aggiornamento dell'attributo _msg col texto del
                  TexField referenziato da _controller
                  Così facendo viene ricostruita l'intero widget contenitore
                  _HomePageProvaState con l'effetto di aggiornare il valore del
                  widget Text qui sotto
                */
                setState(() {
                  _msg = _controller.text;
                  _value = double.parse(_controller.text) * 0.857;
                  _conversionValue = 2;
                });
              },
            ),
            Text(_getMessage()),
          ],
        ),
      ),
    );
  }
}
