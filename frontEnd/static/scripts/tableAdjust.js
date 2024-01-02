function loadTable() {
    const ths = document.querySelectorAll('th')
    const btsTable = document.querySelectorAll('.btTable')

    ths.forEach(t => {
        let div = t.children[0]
        if (t.getAttribute('colspan') == 2) {
            div.style.width = `${320}px`
        } else {
            div.style.width = `${160}px`
        }
    })

    btsTable.forEach(bt => {
        bt.style.width = `${100}%`
    })
}