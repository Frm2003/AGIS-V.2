function selectAll() {
    const url = 'http://localhost:8080/AGIS/curso';

    const configuracaoRequisicao = {
        method: 'GET',
    };

    fetch(url, configuracaoRequisicao)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            let tbody = document.querySelector("tbody")

            for (let i = 0; i < data.length; i++) {
                let tr = document.createElement('tr')

                for (let key in data[i]) {
                    let td = document.createElement('td')
                    let div = document.createElement('div')

                    div.textContent = data[i][key]

                    td.insertAdjacentElement('beforeend', div)
                    tr.insertAdjacentElement('beforeend', td)
                }

                for (let a = 0; a < 2; a++) {
                    const text = ['Editar', 'Remover']
                    let td = document.createElement('td')
                    let div = document.createElement('div')
                    let button = document.createElement('button')

                    button.textContent = text[a]
                    button.setAttribute('type', 'submit')
                    button.setAttribute('class', 'btForm btTable')
                    button.setAttribute('style', 'width: 100%')

                    for (let key in data[i]) {
                        if (key == 'cod') {
                            if (a == 0) {
                                button.setAttribute('onclick', `selectById(${data[i][key]})`)
                            } else {
                                button.setAttribute('onclick', `delete(${data[i][key]})`)
                            }
                        }
                    }

                    div.insertAdjacentElement('beforeend', button)
                    td.insertAdjacentElement('beforeend', div)
                    tr.insertAdjacentElement('beforeend', td)
                }

                tbody.insertAdjacentElement('beforeend', tr)
            }
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}

function selectById(cod) {
    const url = `http://localhost:8080/AGIS/curso/${cod}`;

    const configuracaoRequisicao = {
        method: 'GET',
    };

    fetch(url, configuracaoRequisicao)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            let campos = document.querySelectorAll('.crudForm input[name]')
            const vals = []

            for (let key in data) {
                vals.push(data[key])
            }

            for (let a = 0; a < campos.length; a++) {
                campos[a].value = vals[a]
            }
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}

function insert() {
    fetch('http://localhost:8080/AGIS/curso', {
        method: "POST",
        mode: "cors",
        body: JSON.stringify({
            nome: document.querySelector('input[name="nome"]').value,
            cargaHorario: document.querySelector('input[name="cargaHoraria"]').value,
            sigla: document.querySelector('input[name="sigla"]').value,
            enade: document.querySelector('input[name="enade"]').value,
            turno: document.querySelector('input[name="turno"]').value
        }),
        headers: {
            'Content-type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Resposta:', data);
            limpaCampos()
            removeTable()
            selectAll()
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}

function update() {
    let cod = document.querySelector('input[name="cod"]').value
    fetch(`http://localhost:8080/AGIS/curso/${cod}`, {
        method: "PUT",
        mode: "cors",
        body: JSON.stringify({
            nome: document.querySelector('input[name="nome"]').value,
            cargaHorario: document.querySelector('input[name="cargaHoraria"]').value,
            sigla: document.querySelector('input[name="sigla"]').value,
            enade: document.querySelector('input[name="enade"]').value,
            turno: document.querySelector('input[name="turno"]').value
        }),
        headers: {
            'Content-type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Resposta:', data);
            limpaCampos()
            removeTable()
            selectAll()
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}

function removeTable() {
    let trs = document.querySelectorAll("tbody tr")
    trs.forEach(tr => {
        tr.remove()
    })
}

function limpaCampos() {
    let campos = document.querySelectorAll('input[name]')
    campos.forEach(t => { t.value = '' })
}