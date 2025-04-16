// script.js - Archivo JavaScript optimizado

document.addEventListener('DOMContentLoaded', function() {
    // --- Menú Hamburguesa ---
    const menuToggle = document.querySelector('.menu-toggle');
    const nav = document.querySelector('nav');

    if (menuToggle && nav) {
        menuToggle.addEventListener('click', function() {
            nav.classList.toggle('active');
            const expanded = menuToggle.getAttribute('aria-expanded') === 'true' || false;
            menuToggle.setAttribute('aria-expanded', !expanded);
        });
    }

    // --- Ventanas Modales de Investigadores ---
    const investigadoresContainer = document.getElementById('investigadores-container');
    const investigadores = investigadoresContainer ? investigadoresContainer.querySelectorAll('.foto-perfil img, .investigador-info h3') : [];
    const modalsInvestigadores = {};

    const investigadoresData = [
        {
            id: 'modal-jeffrey',
            nombre: 'Jeffrey Brown',
            cargo: 'LÍDER CREATIVO',
            descripcion: 'Descripción detallada de Jeffrey Brown y su experiencia.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'images/jeffrey-brown-large.webp'
        },
        {
            id: 'modal-ann',
            nombre: 'Ann Richmond',
            cargo: 'GERENTE',
            descripcion: 'Información completa sobre Ann Richmond y sus responsabilidades.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'images/ann-richmond-large.webp'
        },
        {
            id: 'modal-alex',
            nombre: 'Alex Grinfield',
            cargo: 'GURÚ DE LA PROGRAMACIÓN',
            descripcion: 'Detalles sobre la experiencia de Alex Grinfield en programación.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'images/alex-grinfield-large.webp'
        },
        {
            id: 'modal-roxie',
            nombre: 'Roxie Swanson',
            cargo: 'GERENTE DE VENTAS',
            descripcion: 'Descripción del rol y logros de Roxie Swanson.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'images/roxie-swanson-large.webp'
        },
        {
            id: 'modal-investigador5',
            nombre: 'Nombre Investigador 5',
            cargo: 'Cargo del Investigador 5',
            descripcion: 'Descripción detallada del Investigador 5.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'images/placeholder-large.webp'
        },
        {
            id: 'modal-investigador6',
            nombre: 'Nombre Investigador 6',
            cargo: 'Cargo del Investigador 6',
            descripcion: 'Descripción detallada del Investigador 6.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'images/placeholder-large.webp'
        }
        // Agrega más datos de investigadores aquí
    ];

    investigadoresData.forEach(data => {
        const modalDiv = document.createElement('div');
        modalDiv.id = data.id;
        modalDiv.classList.add('modal');
        modalDiv.innerHTML = `
            <div class="modal-content">
                <button class="close-button" aria-label="Cerrar ventana">&times;</button>
                <img src="${data.imagen}" alt="${data.nombre}" width="150" height="150" style="border-radius: 50%; display: block; margin: 0 auto 1em;">
                <h3>${data.nombre}</h3>
                <p class="cargo">${data.cargo}</p>
                <p class="descripcion">${data.descripcion}</p>
                <div class="redes-sociales">
                    <a href="${data.redes.facebook}" target="_blank" aria-label="Enlace a Facebook"><img src="images/facebook.svg" alt="Facebook" width="30" height="30"></a>
                    <a href="${data.redes.twitter}" target="_blank" aria-label="Enlace a Twitter"><img src="images/twitter.svg" alt="Twitter" width="30" height="30"></a>
                    <a href="${data.redes.instagram}" target="_blank" aria-label="Enlace a Instagram"><img src="images/instagram.svg" alt="Instagram" width="30" height="30"></a>
                </div>
            </div>
        `;
        document.body.appendChild(modalDiv);
        modalsInvestigadores[data.id] = modalDiv;

        const closeButton = modalDiv.querySelector('.close-button');
        if (closeButton) {
            closeButton.addEventListener('click', () => {
                modalDiv.style.display = 'none';
            });
        }
    });

    investigadores.forEach(item => {
        const modalId = item.getAttribute('data-modal');
        if (modalId) {
            item.addEventListener('click', () => {
                if (modalsInvestigadores[modalId]) {
                    modalsInvestigadores[modalId].style.display = 'block';
                }
            });
            item.addEventListener('keydown', (event) => {
                if (event.key === 'Enter' || event.key === ' ') {
                    event.preventDefault();
                    if (modalsInvestigadores[modalId]) {
                        modalsInvestigadores[modalId].style.display = 'block';
                    }
                }
            });
        }
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
    let scrollStep = 0;

    const calcularScrollStep = () => {
        const primerInvestigador = investigadoresContainer ? investigadoresContainer.querySelector('.investigador') : null;
        scrollStep = primerInvestigador ? primerInvestigador.offsetWidth + parseFloat(getComputedStyle(primerInvestigador).marginRight) : 0;
    };

    if (investigadoresContainer) {
        calcularScrollStep();
        window.addEventListener('resize', calcularScrollStep);

        if (flechaIzquierda && flechaDerecha) {
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

            const checkScrollButtons = () => {
                flechaIzquierda.style.display = investigadoresContainer.scrollLeft > 0 ? 'block' : 'none';
                flechaDerecha.style.display = investigadoresContainer.scrollLeft < investigadoresContainer.scrollWidth - investigadoresContainer.clientWidth ? 'block' : 'none';
            };

            investigadoresContainer.addEventListener('scroll', checkScrollButtons);
            window.addEventListener('resize', checkScrollButtons);
            checkScrollButtons(); // Llamar al inicio
        }
    }

    // --- Ventanas Modales de Cursos ---
    const cursosTematicas = document.querySelectorAll('#cursos-tematicas .curso-tema');
    const modalsCursos = {};

    cursosTematicas.forEach(item => {
        const modalId = item.getAttribute('data-modal');
        if (modalId) {
            item.addEventListener('click', () => {
                mostrarModalCurso(modalId);
            });
            item.addEventListener('keydown', (event) => {
                if (event.key === 'Enter' || event.key === ' ') {
                    event.preventDefault();
                    mostrarModalCurso(modalId);
                }
            });
        }
    });

    function mostrarModalCurso(modalId) {
        if (!modalsCursos[modalId]) {
            const modalDiv = document.createElement('div');
            modalDiv.id = modalId;
            modalDiv.classList.add('modal');
            let contenidoModal = '';

            switch (modalId) {
                case 'modal-quimica':
                    contenidoModal = `
                        <div class="modal-content">
                            <button class="close-button" aria-label="Cerrar ventana">&times;</button>
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
                            <img src="images/quimica-detalle.webp" alt="Detalles de Química" width="400" height="300">
                        </div>
                    `;
                    break;
                case 'modal-fisica':
                    contenidoModal = `
                        <div class="modal-content">
                            <button class="close-button" aria-label="Cerrar ventana">&times;</button>
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
                            <img src="images/fisica-detalle.webp" alt="Detalles de Física" width="400" height="300">
                        </div>
                    `;
                    break;
                case 'modal-matematica':
                    contenidoModal = `
                        <div class="modal-content">
                            <button class="close-button" aria-label="Cerrar ventana">&times;</button>
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
                            <img src="images/matematica-detalle.webp" alt="Detalles de Matemática" width="400" height="300">
                        </div>
                    `;
                    break;
                case 'modal-ciencia-datos':
                    contenidoModal = `
                        <div class="modal-content">
                            <button class="close-button" aria-label="Cerrar ventana">&times;</button>
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
                            <img src="images/ciencia-datos-detalle.webp" alt="Detalles de Ciencia de Datos" width="400" height="300">
                        </div>
                    `;
                    break;
                case 'modal-programacion':
                    contenidoModal = `
                        <div class="modal-content">
                            <button class="close-button" aria-label="Cerrar ventana">&times;</button>
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
                            <img src="images/programacion-detalle.webp" alt="Detalles de Programación" width="400" height="300">
                        </div>
                    `;
                    break;
                default:
                    contenidoModal = `
                        <div class="modal-content">
                            <button class="close-button" aria-label="Cerrar ventana">&times;</button>
                            <h3>${modalId.replace('modal-', '').replace('-', ' ')}</h3>
                            <p>Contenido específico para este curso.</p>
                            <img src="images/curso-default.webp" alt="Detalles del Curso" width="400" height="300">
                        </div>
                    `;
                    break;
            }

            modalDiv.innerHTML = contenidoModal;
            document.body.appendChild(modalDiv);
            modalsCursos[modalId] = modalDiv;

            const closeButton = modalDiv.querySelector('.close-button');
            if (closeButton) {
                closeButton.addEventListener('click', () => {
                    modalDiv.style.display = 'none';
                });
            }
            modalDiv.style.display = 'block';
        } else if (modalsCursos[modalId]) {
            modalsCursos[modalId].style.display = 'block';
        }
    }
});