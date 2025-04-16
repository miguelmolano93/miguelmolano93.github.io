// script.js

document.addEventListener('DOMContentLoaded', function() {
    // --- Menú Hamburguesa ---
    const menuToggle = document.querySelector('.menu-toggle');
    const nav = document.querySelector('nav');

    menuToggle.addEventListener('click', function() {
        nav.classList.toggle('active');
    });

    // --- Ventanas Modales de Investigadores ---
    const investigadoresContainer = document.getElementById('investigadores-container');
    const investigadores = investigadoresContainer.querySelectorAll('.foto-perfil img, .investigador-info h3');
    const modalsInvestigadores = {};

    const investigadoresData = [
        {
            id: 'modal-jeffrey',
            nombre: 'Jeffrey Brown',
            cargo: 'LÍDER CREATIVO',
            descripcion: 'Descripción detallada de Jeffrey Brown y su experiencia.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'jeffrey-brown-large.jpg'
        },
        {
            id: 'modal-ann',
            nombre: 'Ann Richmond',
            cargo: 'GERENTE',
            descripcion: 'Información completa sobre Ann Richmond y sus responsabilidades.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'ann-richmond-large.jpg'
        },
        {
            id: 'modal-alex',
            nombre: 'Alex Grinfield',
            cargo: 'GURÚ DE LA PROGRAMACIÓN',
            descripcion: 'Detalles sobre la experiencia de Alex Grinfield en programación.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'alex-grinfield-large.jpg'
        },
        {
            id: 'modal-roxie',
            nombre: 'Roxie Swanson',
            cargo: 'GERENTE DE VENTAS',
            descripcion: 'Descripción del rol y logros de Roxie Swanson.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'roxie-swanson-large.jpg'
        },
        {
            id: 'modal-investigador5',
            nombre: 'Investigador 5',
            cargo: 'Cargo del Investigador 5',
            descripcion: 'Descripción detallada del Investigador 5.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'placeholder-large.jpg'
        },
        {
            id: 'modal-investigador6',
            nombre: 'Investigador 6',
            cargo: 'Cargo del Investigador 6',
            descripcion: 'Descripción detallada del Investigador 6.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'placeholder-large.jpg'
        }
        // Agrega más datos de investigadores aquí
    ];

    investigadoresData.forEach(data => {
        const modalDiv = document.createElement('div');
        modalDiv.id = data.id;
        modalDiv.classList.add('modal');
        modalDiv.innerHTML = `
            <div class="modal-content">
                <span class="close-button">&times;</span>
                <img src="${data.imagen}" alt="${data.nombre}" style="width: 150px; height: auto; border-radius: 50%; display: block; margin: 0 auto 1em;">
                <h3>${data.nombre}</h3>
                <p class="cargo">${data.cargo}</p>
                <p class="descripcion">${data.descripcion}</p>
                <div class="redes-sociales">
                    <a href="${data.redes.facebook}" target="_blank"><img src="facebook.png" alt="Facebook"></a>
                    <a href="${data.redes.twitter}" target="_blank"><img src="twitter.png" alt="Twitter"></a>
                    <a href="${data.redes.instagram}" target="_blank"><img src="instagram.png" alt="Instagram"></a>
                </div>
            </div>
        `;
        document.body.appendChild(modalDiv);
        modalsInvestigadores[data.id] = modalDiv;

        const closeButton = modalDiv.querySelector('.close-button');
        closeButton.addEventListener('click', () => {
            modalDiv.style.display = 'none';
        });
    });

    investigadores.forEach(item => {
        const modalId = item.getAttribute('data-modal');
        item.addEventListener('click', () => {
            if (modalId && modalsInvestigadores[modalId]) {
                modalsInvestigadores[modalId].style.display = 'block';
            }
        });
    });

    window.addEventListener('click', event => {
        for (const modalId in modalsInvestigadores) {
            if (event.target === modalsInvestigadores[modalId]) {
                modalsInvestigadores[modalId].style.display = 'none';
            }
        }
        for (const modalId in modalsCursos) {
            if (event.target === modalsCursos[modalId]) {
                modalsCursos[modalId].style.display = 'none';
            }
        }
    });

    // --- Carrusel de Investigadores ---
    const flechaIzquierda = document.querySelector('#investigadores .flecha-izquierda');
    const flechaDerecha = document.querySelector('#investigadores .flecha-derecha');
    let scrollAmount = 0;
    const scrollStep = investigadoresContainer.querySelector('.investigador').offsetWidth + 30; // Ancho de un investigador + margen

    if (flechaIzquierda && flechaDerecha && investigadoresContainer) {
        flechaIzquierda.addEventListener('click', () => {
            scrollAmount -= scrollStep;
            if (scrollAmount < 0) {
                scrollAmount = 0;
            }
            investigadoresContainer.scrollTo({ left: scrollAmount, behavior: 'smooth' });
        });

        flechaDerecha.addEventListener('click', () => {
            scrollAmount += scrollStep;
            if (scrollAmount > investigadoresContainer.scrollWidth - investigadoresContainer.clientWidth) {
                scrollAmount = investigadoresContainer.scrollWidth - investigadoresContainer.clientWidth;
            }
            investigadoresContainer.scrollTo({ left: scrollAmount, behavior: 'smooth' });
        });

        function checkScrollButtons() {
            flechaIzquierda.style.display = investigadoresContainer.scrollLeft > 0 ? 'block' : 'none';
            flechaDerecha.style.display = investigadoresContainer.scrollLeft < investigadoresContainer.scrollWidth - investigadoresContainer.clientWidth ? 'block' : 'none';
        }

        investigadoresContainer.addEventListener('scroll', checkScrollButtons);
        window.addEventListener('resize', checkScrollButtons);
        checkScrollButtons(); // Llamar al inicio
    }

    // --- Ventanas Modales de Cursos ---
    const cursosTematicas = document.querySelectorAll('#cursos-tematicas .curso-tema');
    const modalsCursos = {};

    cursosTematicas.forEach(item => {
        const modalId = item.getAttribute('data-modal');
        item.addEventListener('click', () => {
            if (!modalsCursos[modalId]) {
                const modalDiv = document.createElement('div');
                modalDiv.id = modalId;
                modalDiv.classList.add('modal');
                let contenidoModal = '';

                switch (modalId) {
                    case 'modal-quimica':
                        contenidoModal = `
                            <div class="modal-content">
                                <span class="close-button">&times;</span>
                                <h3>Química</h3>
                                <p>Explora los fundamentos de la química y sus diversas ramas:</p>
                                <ul>
                                    <li>Estructura atómica</li>
                                    <li>Tabla periódica</li>
                                    <li>Enlaces químicos</li>
                                    <li>Reacciones químicas</li>
                                    <li>Estequiometría</li>
                                    <li>Química Orgánica (Hidrocarburos, Grupos funcionales...)</li>
                                    <li>Química Inorgánica (Compuestos iónicos y covalentes...)</li>
                                    <li>Química Analítica (Gravimetría, Volumetría...)</li>
                                    <li>Fisicoquímica (Termodinámica química, Cinética química...)</li>
                                </ul>
                                <img src="quimica-detalle.jpg" alt="Detalles de Química">
                            </div>
                        `;
                        break;
                    case 'modal-fisica':
                        contenidoModal = `
                            <div class="modal-content">
                                <span class="close-button">&times;</span>
                                <h3>Física</h3>
                                <p>Descubre las leyes que rigen el universo:</p>
                                <ul>
                                    <li>Mecánica (Cinemática, Dinámica...)</li>
                                    <li>Termodinámica (Temperatura y calor...)</li>
                                    <li>Ondas y Sonido (Tipos de ondas...)</li>
                                    <li>Óptica (Reflexión y refracción...)</li>
                                    <li>Electricidad y Magnetismo (Ley de Ohm...)</li>
                                    <li>Física Moderna (Teoría cuántica, Relatividad...)</li>
                                </ul>
                                <img src="fisica-detalle.jpg" alt="Detalles de Física">
                            </div>
                        `;
                        break;
                    case 'modal-matematica':
                        contenidoModal = `
                            <div class="modal-content">
                                <span class="close-button">&times;</span>
                                <h3>Matemática</h3>
                                <p>Desarrolla tu pensamiento lógico y habilidades de resolución de problemas:</p>
                                <ul>
                                    <li>Aritmética (Operaciones básicas...)</li>
                                    <li>Álgebra (Expresiones algebraicas...)</li>
                                    <li>Geometría (Puntos, rectas y planos...)</li>
                                    <li>Trigonometría (Razones trigonométricas...)</li>
                                    <li>Cálculo Diferencial e Integral (Límites, Derivadas...)</li>
                                    <li>Estadística y Probabilidad (Medidas de tendencia central...)</li>
                                    <li>Matemática Discreta (Lógica proposicional...)</li>
                                    <li>Álgebra Lineal (Vectores, Matrices...)</li>
                                    <li>Ecuaciones Diferenciales (Ecuaciones de primer orden...)</li>
                                </ul>
                                <img src="matematica-detalle.jpg" alt="Detalles de Matemática">
                            </div>
                        `;
                        break;
                    case 'modal-ciencia-datos':
                        contenidoModal = `
                            <div class="modal-content">
                                <span class="close-button">&times;</span>
                                <h3>Ciencia de Datos</h3>
                                <p>Aprende a extraer conocimiento valioso a partir de grandes conjuntos de datos:</p>
                                <ul>
                                    <li>Fundamentos de Ciencia de Datos (Estadística descriptiva...)</li>
                                    <li>Limpieza y Visualización de Datos (Pandas y Numpy...)</li>
                                    <li>Machine Learning (Aprendizaje supervisado...)</li>
                                    <li>Deep Learning (Redes neuronales...)</li>
                                    <li>Big Data (Hadoop, Spark...)</li>
                                    <li>Proyectos de Ciencia de Datos (Finanzas, Salud...)</li>
                                </ul>
                                <img src="ciencia-datos-detalle.jpg" alt="Detalles de Ciencia de Datos">
                            </div>
                        `;
                        break;
                    case 'modal-programacion':
                        contenidoModal = `
                            <div class="modal-content">
                                <span class="close-button">&times;</span>
                                <h3>Programación</h3>
                                <p>Adquiere las habilidades para crear software y automatizar tareas:</p>
                                <ul>
                                    <li>Fundamentos de Programación (Algoritmos y lógica...)</li>
                                    <li>Lenguajes de Programación (Python, JavaScript...)</li>
                                    <li>Desarrollo Web (HTML, CSS, JS...)</li>
                                    <li>Bases de Datos (SQL, NoSQL...)</li>
                                    <li>Estructuras de Datos y Algoritmos (Listas, pilas...)</li>
                                    <li>Automatización y Web Scraping (Selenium, BeautifulSoup...)</li>
                                </ul>
                                <img src="programacion-detalle.jpg" alt="Detalles de Programación">
                            </div>
                        `;
                        break;
                    default:
                        contenidoModal = `
                            <div class="modal-content">
                                <span class="close-button">&times;</span>
                                <h3>${modalId.replace('modal-', '').replace('-', ' ')}</h3>
                                <p>Contenido específico para este curso.</p>
                                <img src="curso-default.jpg" alt="Detalles del Curso">
                            </div>
                        `;
                        break;
                }

                modalDiv.innerHTML = contenidoModal;
                document.body.appendChild(modalDiv);
                modalsCursos[modalId] = modalDiv;

                const closeButton = modalDiv.querySelector('.close-button');
                closeButton.addEventListener('click', () => {
                    modalDiv.style.display = 'none';
                });

                modalDiv.style.display = 'block';
            } else if (modalsCursos[modalId]) {
                modalsCursos[modalId].style.display = 'block';
            }
        });
    });
});