document.addEventListener('DOMContentLoaded', () => {
    console.log('DOM totalmente carregado e analisado');
    const toggleButton = document.getElementById('toggle-mode');
    const body = document.body;
    const header = document.querySelector('header');
    const footer = document.querySelector('footer');

    if (toggleButton) {
        console.log('Botão encontrado');
        toggleButton.addEventListener('click', () => {
            console.log('Botão clicado');

            // Verificar classes antes de alternar
            console.log('Classes antes de alternar:', {
                body: body.classList.toString(),
                header: header.classList.toString(),
                footer: footer.classList.toString(),
                button: toggleButton.classList.toString()
            });

            // Alternar as classes
            body.classList.toggle('light-mode');
            header.classList.toggle('light-mode');
            footer.classList.toggle('light-mode');
            toggleButton.classList.toggle('light-mode');

            // Verificar classes depois de alternar
            console.log('Classes depois de alternar:', {
                body: body.classList.toString(),
                header: header.classList.toString(),
                footer: footer.classList.toString(),
                button: toggleButton.classList.toString()
            });
        });
    } else {
        console.log('Botão não encontrado');
    }
});
