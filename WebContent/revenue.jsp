<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FINTECH</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .navbar {
            background-color: rgba(255, 255, 255, 0.8);
        }
        .content {
            margin-top: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 8px;
        }
        .receita-box {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
        }
        .receita-box.selected {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="#">FINTECH</a>
    <div class="collapse navbar-collapse"></div>
</nav>

<div class="container">
    <div class="row justify-content-end mb-4">
        <div class="col-md-6 text-right">
            <button class="btn btn-outline-primary mr-2" onclick="location.href='login.jsp'">Trocar Usuário</button>
            <button class="btn btn-outline-secondary" onclick="location.href='#'">Acessar Carteira</button>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-6 content text-center">

            <div class="mt-4">
                <h5>Receita de Banco</h5>
                <p>Aqui você pode visualizar, criar, editar e deletar suas receitas bancárias.</p>
                <!-- Container para as receitas -->
                <div id="receitas-container"></div>
            </div>

            <button class="btn btn-outline-success btn-block" onclick="criarReceita()">Criar Receita</button>
            <button class="btn btn-outline-success btn-block" onclick="editarReceita()">Editar Receita</button>
            <button class="btn btn-outline-success btn-block" onclick="deletarReceita()">Deletar Receita</button>

        </div>
    </div>
</div>

<script>
    // Array para armazenar as receitas
    let receitas = [];

    // Função para criar uma nova receita
    function criarReceita() {
        let nome = prompt("Digite o nome da receita:");
        let valor = parseFloat(prompt("Digite o valor da receita (ex: 1000.00):"));

        if (nome && !isNaN(valor)) {
            // Verificar se já existe uma receita com o mesmo nome
            let receitaExistente = receitas.find(r => r.nome === nome);
            if (receitaExistente) {
                alert("Já existe uma receita com esse nome. Por favor, escolha outro nome.");
                return;
            }

            let novaReceita = {
                nome: nome,
                valor: valor
            };

            receitas.push(novaReceita);

            atualizarListaReceitas();
        } else {
            alert("Por favor, preencha corretamente o nome e o valor da receita.");
        }
    }

    // Função para editar uma receita existente pelo nome
    function editarReceita() {
        let nome = prompt("Digite o nome da receita que deseja editar:");

        if (nome) {
            // Encontrar a receita pelo nome
            let receitaIndex = receitas.findIndex(r => r.nome === nome);

            if (receitaIndex !== -1) {
                let novoValor = parseFloat(prompt("Digite o novo valor da receita (ex: 1000.00):"));

                if (!isNaN(novoValor)) {
                    // Atualizar o valor da receita
                    receitas[receitaIndex].valor = novoValor;

                    // Atualizar a exibição das receitas na página
                    atualizarListaReceitas();
                } else {
                    alert("Valor inválido. Por favor, digite um valor numérico.");
                }
            } else {
                alert("Não foi encontrada uma receita com esse nome.");
            }
        }
    }

    // Função para deletar uma receita existente pelo nome
    function deletarReceita() {
        let nome = prompt("Digite o nome da receita que deseja deletar:");

        if (nome) {
            // Encontrar a receita pelo nome
            let receitaIndex = receitas.findIndex(r => r.nome === nome);

            if (receitaIndex !== -1) {
                // Remover a receita do array
                receitas.splice(receitaIndex, 1);

                // Atualizar a exibição das receitas na página
                atualizarListaReceitas();
            } else {
                alert("Não foi encontrada uma receita com esse nome.");
            }
        }
    }

    // Função para atualizar a exibição das receitas na página
    function atualizarListaReceitas() {
        let receitasContainer = document.getElementById('receitas-container');
        receitasContainer.innerHTML = ''; // Limpar o conteúdo atual

        receitas.forEach((receita, index) => {
            let receitaHTML = `
                <div class="receita-box" onclick="selecionarReceita(${index})">
                    <strong>${receita.nome}</strong><br>
                    Valor: R$ ${receita.valor.toFixed(2)}
                </div>
            `;
            receitasContainer.innerHTML += receitaHTML;
        });
    }

    // Função para selecionar uma receita para edição ou deleção
    let receitaSelecionadaIndex = -1;
    function selecionarReceita(index) {
        receitaSelecionadaIndex = index;

        // Estilizar visualmente a receita selecionada
        let receitasBoxes = document.querySelectorAll('.receita-box');
        receitasBoxes.forEach((box, idx) => {
            box.classList.remove('selected');
            if (idx === index) {
                box.classList.add('selected');
            }
        });
    }
</script>
</body>
</html>
