# 🌀 Projeto Estrutura de Dados – Jogo do Labirinto

Este projeto implementa um **jogo de labirinto em Java**, desenvolvido como parte da disciplina de **Estrutura de Dados**.  
O jogador percorre mapas em arquivos `.txt`, realiza movimentos e registra sua pontuação em um ranking persistente.

---

## 📂 Estrutura do Projeto

ProjetoEstrutura-main/
├── src/ # Código-fonte em Java
│ └── br/mack/labirinto # Pacotes organizados (app, core, ds, io, model, util)
├── out/ # Arquivos compilados (.class)
├── mapas/ # Mapas em formato .txt
├── ranking.csv # Ranking de pontuações
└── README.md # Este arquivo

yaml
Copiar código

---

## 🚀 Como Compilar e Executar

### 1️⃣ Compilar o código
No terminal, dentro da pasta raiz do projeto, execute:

```bash
javac -d out $(find src -name "*.java")
Isso compila todos os arquivos .java da pasta src e gera os .class na pasta out/.

2️⃣ Executar o jogo
Use o comando:

bash
Copiar código
java -cp out br.mack.labirinto.app.Game --map=./mapas/map1.txt --seed=123 --player=Jogador
Parâmetros disponíveis:

--map → caminho para o arquivo do mapa (.txt)

--seed → define a semente para geração de números aleatórios

--player → nome do jogador exibido no ranking

Exemplo com outro mapa:

bash
Copiar código
java -cp out br.mack.labirinto.app.Game --map=./mapas/map2.txt --seed=42 --player=Beatriz
🎮 Como Jogar
O tabuleiro será mostrado no terminal.

Insira os comandos de movimento solicitados pelo jogo.

O objetivo é encontrar a saída do labirinto.

Ao final, sua pontuação é salva em ranking.csv e o ranking é atualizado automaticamente.

👨‍💻 Autores
Murilo Franciscon

#Mariana Agostinho

Beatriz Soares
