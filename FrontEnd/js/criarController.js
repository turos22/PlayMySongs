  document.getElementById('form-musica').addEventListener('submit', async function(e) {
            e.preventDefault();

            const url = 'http://localhost:8080/mysong';
            const estilo = document.getElementById('estilo').value;
            const nome = document.getElementById('nome').value;
            const artista = document.getElementById('artista').value;
            const audioFile = document.getElementById('audio').files[0];

            const formData = new FormData();
            formData.append('estilo', estilo);
            formData.append('titulo', nome);
            formData.append('artista', artista);
            formData.append('audio', audioFile);

            try {
                const response = await fetch(url + '/add-music', {
                    method: 'POST',
                    body: formData
                });

                if (response.ok) {
                    document.getElementById('mensagem').style.color = 'green';
                    document.getElementById('mensagem').textContent = 'Música cadastrada com sucesso!';
                    document.getElementById('form-musica').reset();
                } else {
                    const erro = await response.text();
                    document.getElementById('mensagem').style.color = 'red';
                    document.getElementById('mensagem').textContent = 'Erro: ' + erro;
                }
            } catch (err) {
                document.getElementById('mensagem').style.color = 'red';
                document.getElementById('mensagem').textContent = 'Erro de conexão.';
            }
        });