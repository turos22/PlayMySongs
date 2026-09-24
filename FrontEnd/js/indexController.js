 async function carregarMusicas() {
            try {
                const url = 'http://localhost:8080/mysong';
                const response = await fetch(url + '/allmusics');
                const musicas = await response.json(); 
                const container = document.getElementById('lista-musicas');
                container.innerHTML = '';

                musicas.forEach(musica => {
                    const card = document.createElement('div');
                    card.className = 'music-card';
                    card.innerHTML = `
                        <div>
                            <strong>${musica.nome}</strong> - ${musica.artista} <br>
                            <small>Estilo: ${musica.estilo}</small>
                        </div>
                        <div>
                            <audio controls src="http://localhost:8080/uploads/${musica.caminho_mp3}"></audio>
                        </div>
                    `;
                    container.appendChild(card);
                });
            } catch (e) {
                console.error("Erro ao carregar músicas:", e);
                document.getElementById('lista-musicas').innerHTML = '<p>Erro ao carregar músicas.</p>';
            }
        }

        carregarMusicas();