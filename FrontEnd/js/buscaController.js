const url = 'http://localhost:8080/mysong';

document.addEventListener("DOMContentLoaded", async () => {
    const selectEstilo = document.getElementById("estilo");

    try {
        const response = await fetch(url + '/get-music-styles');

        if (!response.ok) {
            throw new Error("Não foi possível carregar os estilos musicais.");
        }

        const estilos = await response.json();

        selectEstilo.innerHTML = '<option value="">Todos os estilos</option>';

        estilos.forEach(estilo => {
            const option = document.createElement("option");
            option.value = estilo;
            option.textContent = estilo;
            selectEstilo.appendChild(option);
        });

    } catch (error) {
        console.error("Erro ao buscar estilos:", error);
        selectEstilo.innerHTML = '<option value="">Erro ao carregar estilos</option>';
    }

    carregarMusicas(url + '/allmusics');
});

document.getElementById('form-busca').addEventListener('submit', function(e) {
    e.preventDefault();

    const titulo = document.getElementById('nome').value;
    const estilo = document.getElementById('estilo').value;
    const artista = document.getElementById('artista').value;

    carregarMusicas(url + '/find-musics?titulo=' + encodeURIComponent(titulo)
        + '&estilo=' + encodeURIComponent(estilo)
        + '&artista=' + encodeURIComponent(artista));
});

async function carregarMusicas(endereco) {
    const container = document.getElementById('lista-musicas');
    const mensagem = document.getElementById('mensagem');
    container.innerHTML = '';
    mensagem.textContent = '';

    try {
        const response = await fetch(endereco);
        const dados = await response.json();

        if (!response.ok) {
            mensagem.style.color = 'red';
            mensagem.textContent = dados.mensagem;
            return;
        }

        dados.forEach(musica => {
            const card = document.createElement('div');
            card.className = 'music-card';
            card.innerHTML = `
                <div>
                    <strong class="titulo"></strong> - <span class="artista"></span> <br>
                    <small>Estilo: <span class="estilo"></span></small>
                </div>
                <div>
                    <audio controls>
                        <source>
                    </audio>
                </div>
            `;
            card.querySelector('.titulo').textContent = musica.titulo;
            card.querySelector('.artista').textContent = musica.artista;
            card.querySelector('.estilo').textContent = musica.estilo;
            const source = card.querySelector('source');
            source.src = 'http://localhost:8080/uploads/' + musica.caminho_mp3;
            source.type = 'audio/mpeg';
            container.appendChild(card);
        });
    } catch (e) {
        console.error("Erro ao buscar músicas:", e);
        mensagem.style.color = 'red';
        mensagem.textContent = 'Erro de conexão.';
    }
}
