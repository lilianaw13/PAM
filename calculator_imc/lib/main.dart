import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Calculator IMC',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const IMCScreen(),
    );
  }
}

class IMCScreen extends StatefulWidget {
  const IMCScreen({super.key});

  @override
  State<IMCScreen> createState() => _IMCScreenState();
}

class _IMCScreenState extends State<IMCScreen> {
  final TextEditingController greutateController = TextEditingController();
  final TextEditingController inaltimeController = TextEditingController();

  String rezultat = '';

  void calculeazaIMC() {
    double? greutate = double.tryParse(greutateController.text);
    double? inaltimeCm = double.tryParse(inaltimeController.text);

    if (greutate == null || inaltimeCm == null || inaltimeCm <= 0) {
      setState(() {
        rezultat = 'Introduceți valori valide!';
      });
      return;
    }

    double inaltimeM = inaltimeCm / 100;
    double imc = greutate / (inaltimeM * inaltimeM);

    String categorie;

    if (imc < 18.5) {
      categorie = 'Subponderal';
    } else if (imc < 25) {
      categorie = 'Greutate normală';
    } else if (imc < 30) {
      categorie = 'Supraponderal';
    } else {
      categorie = 'Obezitate';
    }

    setState(() {
      rezultat = 'IMC: ${imc.toStringAsFixed(2)}\nCategoria: $categorie';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Calculator IMC'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            TextField(
              controller: greutateController,
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                labelText: 'Greutate (kg)',
                border: OutlineInputBorder(),
              ),
            ),

            const SizedBox(height: 20),

            TextField(
              controller: inaltimeController,
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                labelText: 'Înălțime (cm)',
                border: OutlineInputBorder(),
              ),
            ),

            const SizedBox(height: 20),

            ElevatedButton(
              onPressed: calculeazaIMC,
              child: const Text('Calculează IMC'),
            ),

            const SizedBox(height: 30),

            Text(
              rezultat,
              textAlign: TextAlign.center,
              style: const TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }
}