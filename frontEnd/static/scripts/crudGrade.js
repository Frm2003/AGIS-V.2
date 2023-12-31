// VISUALIZARGRADE.HTML
function selectAll() {
    selectCursos()

    const url = 'http://localhost:8080/AGIS/gradeCurricular'

    const configuracaoRequisicao = {
        method: 'GET',
    }

    fetch(url, configuracaoRequisicao)
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
        }
        return response.json()
    })
    .then(data => {
        data.forEach(grade => {
            let section = document.createElement('section')
            section.setAttribute('class', 'card')

            let h3 = document.createElement('h3')
            h3.textContent = grade.curso.sigla + ' - ' + grade.curso.turno
            section.insertAdjacentElement('afterbegin', h3)

            let article = document.createElement('article')
            
            let p = document.createElement('p')
            p.textContent = 'Semestre: ' + grade.semestre
            article.insertAdjacentElement('afterbegin', p)
            p = document.createElement('p')
            p.textContent = 'Ano: ' + grade.ano
            article.insertAdjacentElement('afterbegin', p)

            let div = document.createElement('div')
            div.setAttribute('class', 'alingBt')
            let button = document.createElement('button')
            button.setAttribute('class', 'btForm')
            button.textContent = 'Editar'
            div.insertAdjacentElement('afterbegin', button)

            section.insertAdjacentElement('beforeend', article)
            section.insertAdjacentElement('beforeend', div)
            document.getElementsByClassName('grid')[0].insertAdjacentElement('beforeend', section)
        }) 
    })
    .catch(error => {
        console.error('Erro:', error)
    });

}

function selectCursos() {
    const url = 'http://localhost:8080/AGIS/curso'

    const configuracaoRequisicao = {
        method: 'GET',
    }

    fetch(url, configuracaoRequisicao)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
            }
            return response.json()
        })
        .then(data => {
            let select = document.querySelector('#selectCurso')
            for (let a = 0; a < data.length; a++) {
                let option = document.createElement('option')
                let text = '';
                for (let key in data[a]) {
                    if (key == 'cod') {
                        option.setAttribute('value', data[a][key])
                    }
                    if (key == 'nome') {
                        text = data[a][key] + ' - '
                    }
                    if (key == 'turno') {
                        text += data[a][key]
                    }
                    option.textContent = text
                }
                select.insertAdjacentElement('beforeend', option)
            }
        })
        .catch(error => {
            console.error('Erro:', error)
        });
}

function criar() {
    let codCurso = document.querySelector('#selectCurso').value
    if (codCurso != 'default') {
        localStorage.setItem('codCurso', codCurso)
        window.location.href = "crudGrade.html"
    }
}

// CRUDGRADE.HTML
function selectCurso() {
    let cod = localStorage.getItem('codCurso')

    const url = `http://localhost:8080/AGIS/curso/${cod}`
    const configuracaoRequisicao = { method: 'GET' };

    fetch(url, configuracaoRequisicao)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`)
            }
            return response.json()
        })
        .then(data => {
            let dataAtual = new Date()
            let semestre = 0

            if (dataAtual.getMonth() > 6) { semestre = 2 } else { semestre = 1 }

            document.querySelector('#codCurso').value = data.cod
            document.querySelector('#nome').textContent = data.nome
            document.querySelector('#semestre').textContent = semestre
            document.querySelector('#ano').textContent = dataAtual.getFullYear()

            selectDisciplinasCurso()
        })
        .catch(error => {
            console.error('Erro:', error)
        });
}

function selectDisciplinasCurso() {
    let cod = localStorage.getItem('codCurso')

    const url = `http://localhost:8080/AGIS/disciplina/curso/${cod}`
    const configuracaoRequisicao = { method: 'GET' };

    fetch(url, configuracaoRequisicao)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`)
            }
            return response.json()
        })
        .then(data => {
            let select = document.querySelectorAll('[name="codDisciplina"]')
            data.forEach(p => {
                let option = document.createElement('option')
                option.setAttribute('value', p.cod)
                option.textContent = p.nome
                select.forEach(s => {
                    s.insertAdjacentElement('afterbegin', option)
                })
            })
        })
        .catch(error => {
            console.error('Erro:', error)
        });
}

function selectProfessores() {
    const url = `http://localhost:8080/AGIS/professor`
    const configuracaoRequisicao = { method: 'GET' };

    fetch(url, configuracaoRequisicao)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`)
            }
            return response.json()
        })
        .then(data => {
            let select = document.querySelectorAll('[name="codProfessor"]')
            data.forEach(p => {
                let option = document.createElement('option')
                option.setAttribute('value', p.cod)
                option.textContent = p.usuario.nome
                select.forEach(s => {
                    s.insertAdjacentElement('afterbegin', option)
                })
            })
        })
        .catch(error => {
            console.error('Erro:', error)
        });
}

function insert() {
    fetch('http://localhost:8080/AGIS/gradeCurricular', {
        method: "POST",
        mode: "cors",
        body: JSON.stringify({
            codCurso: document.querySelector('#codCurso').value,
            semestre: document.querySelector('#semestre').textContent,
            ano: document.querySelector('#ano').textContent
        }),
        headers: {
            'Content-type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`)
            }
            return response.json()
        })
        .then(data => {
            console.log('Resposta:', data)
            localStorage.setItem('codGrade', data.cod)
            if (localStorage.getItem('codGrade') != null) {
                insertTurmas()
            }
        })
        .catch(error => {
            console.error('Erro:', error)
        });
}

function insertTurmas() {
    let horaIni = document.querySelectorAll('[name="horaIni"]')
    let horaFim = document.querySelectorAll('[name="horaFim"]')
    let diaDaSemana = document.querySelectorAll('[name="diaDaSemana"]')
    let codDisciplina = document.querySelectorAll('[name="codDisciplina"]')
    let codProfessor = document.querySelectorAll('[name="codProfessor"]')

    const listaDados = []
    for (let i = 0; i < horaIni.length; i++) {
        const objetoDados = {
            horarioInicio: horaIni[i].value,
            horarioFim: horaFim[i].value,
            diaDaSemana: diaDaSemana[i].value,
            codDisciplina: codDisciplina[i].value,
            codProfessor: codProfessor[i].value,
            codGradeCurricular: localStorage.getItem('codGrade')
        };
        listaDados.push(objetoDados)
    }

    listaDados.forEach(dados => {
        fetch('http://localhost:8080/AGIS/turma', {
            method: "POST",
            mode: "cors",
            body: JSON.stringify(dados),
            headers: {
                'Content-type': 'application/json',
            },
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`)
                }
                return response.json()
            })
            .then(data => {
                console.log('Resposta:', data)
                window.location.href = "visualizarGrade.html"
            })
            .catch(error => {
                console.error('Erro:', error)
            })
    })
}

function criaCampos() {
    let tr = document.createElement('tr')

    const names = ['codProfessor', 'codDisciplina', 'diaDaSemana', 'horaFim', 'horaIni']
    const horarios = ['13:00', '14:50', '16:30', '18:20']
    const diasDaSemana = ['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado']

    names.forEach((text, i) => {
        let td = document.createElement('td')
        let select = document.createElement('select')
        let div = document.createElement('div')

        select.setAttribute('name', text)

        if (text == 'horaFim' || text == 'horaIni') {
            horarios.forEach(h => {
                select.insertAdjacentElement('beforeend', criaOptions(h))
            })
        }

        if (text == 'diaDaSemana') {
            diasDaSemana.forEach(d => {
                select.insertAdjacentElement('beforeend', criaOptions(d))
            })
        }

        div.setAttribute('class', 'campo')

        div.insertAdjacentElement('afterbegin', select)
        td.insertAdjacentElement('afterbegin', div)
        tr.insertAdjacentElement('afterbegin', td)
    })

    document.querySelector('#tbody').insertAdjacentElement('beforeend', tr)

    selectDisciplinasCurso(); selectProfessores()
}

function deletaCampos() {
    let trs = document.querySelectorAll('tbody tr')
    trs.forEach((tr, i) => {
        if (i == trs.length - 1) { tr.remove() }
    })
}

function criaOptions(text) {
    let op = document.createElement('option')
    op.text = text
    op.value = text
    return op
} 