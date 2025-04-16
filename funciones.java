document.addEventListener('DOMContentLoaded', function() {
    // --- Ventanas Modales de Investigadores ---
    const investigadores = document.querySelectorAll('#investigadores-container .investigador .foto-perfil img, #investigadores-container .investigador .investigador-info h3');
    const modalsInvestigadores = {};

    investigadores.forEach(item => {
        const modalId = item.getAttribute('data-modal');
        if (modalId) {
            modalsInvestigadores[modalId] = document.getElementById(modalId);
            const closeButton = modalsInvestigadores[modalId]?.querySelector('.close-button');

            item.addEventListener('click', function() {
                if (modalsInvestigadores[modalId]) {
                    modalsInvestigadores[modalId].style.display = 'block';
                }
            });

            if (closeButton) {
                closeButton.addEventListener('click', function() {
                    if (modalsInvestigadores[modalId]) {
                        modalsInvestigadores[modalId].style.display = 'none';
                    }
                });
            }
        }
    });

    window.addEventListener('click', function(event) {
        for (const modalId in modalsInvestigadores) {
            if (event.target === modalsInvestigadores[modalId]) {
                modalsInvestigadores[modalId].style.display = 'none';
            }
        }
    });

    // Crear los elementos de la ventana modal para cada investigador dinámicamente
    const investigadoresData = [
        {
            id: 'modal-jeffrey',
            nombre: 'Jeffrey Brown',
            cargo: 'LÍDER CREATIVO',
            descripcion: 'Descripción detallada de Jeffrey Brown y su experiencia.',
            redes: { facebook: '#', twitter: '#', instagram: '#' },
            imagen: 'jeffrey-brown-large.jpg' // Asegúrate de tener una imagen más grande
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
                    <a href="${data.redes.facebook}"><img src="facebook.png" alt="Facebook"></a>
                    <a href="${data.redes.twitter}"><img src="twitter.png" alt="Twitter"></a>
                    <a href="${data.redes.instagram}"><img src="instagram.png" alt="Instagram"></a>
                </div>
            </div>
        `;
        document.body.appendChild(modalDiv);

        // Asignar el evento de cierre al botón de la modal creada dinámicamente
        const closeButton = modalDiv.querySelector('.close-button');
        closeButton.addEventListener('click', function() {
            modalDiv.style.display = 'none';
        });

        // Agregar la modal al objeto de modales
        modalsInvestigadores[data.id] = modalDiv;
    });

    // --- Carrusel de Investigadores ---
    const investigadoresContainer = document.getElementById('investigadores-container');
    const flechaIzquierda = document.querySelector('#investigadores .flecha-izquierda');
    const flechaDerecha = document.querySelector('#investigadores .flecha-derecha');
    let scrollAmount = 0;
    const scrollStep = 300; // Ajusta este valor según el ancho aproximado de 4 investigadores

    if (flechaIzquierda && flechaDerecha && investigadoresContainer) {
        flechaIzquierda.addEventListener('click', function() {
            scrollAmount -= scrollStep;
            investigadoresContainer.scrollTo({
                left: scrollAmount,
                behavior: 'smooth'
            });
        });

        flechaDerecha.addEventListener('click', function() {
            scrollAmount += scrollStep;
            investigadoresContainer.scrollTo({
                left: scrollAmount,
                behavior: 'smooth'
            });
        });

        // Ocultar/Mostrar flechas si el contenido no se desborda inicialmente
        function checkScroll() {
            if (investigadoresContainer.scrollWidth <= investigadoresContainer.clientWidth) {
                flechaIzquierda.style.display = 'none';
                flechaDerecha.style.display = 'none';
            } else {
                flechaIzquierda.style.display = 'block';
                flechaDerecha.style.display = 'block';
            }
        }

        checkScroll();
        window.addEventListener('resize', checkScroll);
    }

    // --- Ventanas Modales de Cursos ---
    const cursosTematicas = document.querySelectorAll('#cursos-tematicas .curso-tema');
    const modalsCursos = {};

    cursosTematicas.forEach(item => {
        const modalId = item.getAttribute('data-modal');
        if (modalId) {
            item.addEventListener('click', function() {
                if (!modalsCursos[modalId]) {
                    // Crear la modal dinámicamente si no existe
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
                                        <li>Hidrocarburos</li>
                                        <li>Grupos funcionales</li>
                                        <li>Reacciones orgánicas</li>
                                        <li>Nomenclatura</li>
                                        <li>Polímeros</li>
                                        <li>Compuestos iónicos y covalentes</li>
                                        <li>Ácidos y bases</li>
                                        <li>Sales</li>
                                        <li>Química de coordinación</li>
                                        <li>Gravimetría</li>
                                        <li>Volumetría</li>
                                        <li>Cromatografía</li>
                                        <li>Espectroscopía (UV-Vis, IR)</li>
                                    </ul>
                                    <img src="quimica-detalle.jpg" alt="Detalles de Química" style="max-width: 100%;">
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
                                        <li>Cinemática</li>
                                        <li>Dinámica</li>
                                        <li>Trabajo y energía</li>
                                        <li>Movimiento circular</li>
                                        <li>Leyes de Newton</li>
                                        <li>Temperatura y calor</li>
                                        <li>Leyes de la termodinámica</li>
                                        <li>Máquinas térmicas</li>
                                        <li>Tipos de ondas</li>
                                        <li>Propiedades de las ondas</li>
                                        <li>Efecto Doppler</li>
                                        <li>Reflexión y refracción</li>
                                        <li>Lentes y espejos</li>
                                        <li>Interferencia y difracción</li>
                                        <li>Ley de Ohm</li>
                                        <li>Circuitos eléctricos</li>
                                        <li>Campos eléctricos y magnéticos</li>
                                        <li>Leyes de Maxwell</li>
                                        <li>Teoría cuántica</li>
                                        <li>Relatividad</li>
                                        <li>Física nuclear</li>
                                    </ul>
                                    <img src="fisica-detalle.jpg" alt="Detalles de Física" style="max-width: 100%;">
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
                                        <li>Operaciones básicas</li>
                                        <li>Múltiplos y divisores</li>
                                        <li>Potenciación y radicación</li>
                                        <li>Fracciones y decimales</li>
                                        <li>Expresiones algebraicas</li>
                                        <li>Ecuaciones e inecuaciones</li>
                                        <li>Polinomios</li>
                                        <li>Productos notables</li>
                                        <li>Factorización</li>
                                        <li>Puntos, rectas y planos</li>
                                        <li>Figuras planas</li>
                                        <li>Geometría del espacio</li>
                                        <li>Ángulos</li>
                                        <li>Teorema de Pitágoras</li>
                                        <li>Razones trigonométricas</li>
                                        <li>Identidades trigonométricas</li>
                                        <li>Ley de senos y cosenos</li>
                                        <li>Funciones trigonométricas</li>
                                        <li>Límites</li>
                                        <li>Derivadas</li>
                                        <li>Aplicaciones de la derivada</li>
                                        <li>Integrales definidas e indefinidas</li>
                                        <li>Aplicaciones de la integral</li>
                                        <li>Medidas de tendencia central</li>
                                        <li>Dispersión</li>
                                        <li>Probabilidad clásica y frecuencial</li>
                                        <li>Distribuciones</li>
                                        <li>Lógica proposicional</li>
                                        <li>Teoría de conjuntos</li>
                                        <li>Combinatoria</li>
                                        <li>Grafos y árboles</li>
                                        <li>Inducción matemática</li>
                                        <li>Vectores</li>
                                        <li>Matrices y determinantes</li>
                                        <li>Sistemas de ecuaciones lineales</li>
                                        <li>Espacios vectoriales</li>
                                        <li>Ecuaciones de primer orden</li>
                                        <li>Ecuaciones de orden superior</li>
                                        <li>Sistemas de ecuaciones diferenciales</li>
                                    </ul>
                                    <img src="matematica-detalle.jpg" alt="Detalles de Matemática" style="max-width: 100%;">
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
                                        <li>Estadística descriptiva e inferencial</li>
                                        <li>Probabilidades</li>
                                        <li>Análisis exploratorio de datos</li>
                                        <li>Pandas y Numpy</li>
                                        <li>Matplotlib, Seaborn, Plotly</li>
                                        <li>Aprendizaje supervisado</li>
                                        <li>Aprendizaje no supervisado</li>
                                        <li>Scikit-learn, XGBoost</li>
                                        <li>Redes neuronales</li>
                                        <li>TensorFlow y Keras</li>
                                        <li>Procesamiento de imágenes y NLP</li>
                                        <li>Hadoop, Spark</li>
                                        <li>Bases de datos distribuidas</li>
                                        <li>Proyectos de Ciencia de Datos (Finanzas, Salud, Marketing, Análisis de sentimientos, Series temporales)</li>
                                    </ul>
                                    <img src="ciencia-datos-detalle.jpg" alt="Detalles de Ciencia de Datos" style="max-width: 100%;">
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
                                        <li>Algoritmos y lógica</li>
                                        <li>Tipos de datos</li>
                                        <li>Estructuras de control</li>
                                        <li>Funciones</li>
                                        <li>Python</li>
                                        <li>JavaScript</li>
                                        <li>Java</li>
                                        <li>C/C++</li>
                                        <li>HTML, CSS, JS</li>
                                        <li>Frameworks (React, Angular)</li>
                                        <li>Backend (Node.js, Flask, Django)</li>
                                        <li>SQL, NoSQL (MongoDB)</li>
                                        <li>Diseño de bases de datos</li>
                                        <li>Listas, pilas, colas, árboles</li>
                                        <li>Algoritmos de ordenamiento y búsqueda</li>
                                        <li>Selenium, BeautifulSoup, APIs</li>
                                    </ul>
                                    <img src="programacion-detalle.jpg" alt="Detalles de Programación" style="max-width: 100%;">
                                </div>
                            `;
                            break;
                        default:
                            contenidoModal = `
                                <div class="modal-content">
                                    <span class="close-button">&times;</span>
                                    <h3>${modalId.replace('modal-', '').replace('-', ' ')}</h3>
                                    <p>Contenido específico para este curso.</p>
                                    <img src="curso-default.jpg" alt="Detalles del Curso" style="max-width: 100%;">
                                </div>
                            `;
                            break;
                    }

                    modalDiv.innerHTML = contenidoModal;
                    document.body.appendChild(modalDiv);
                    modalsCursos[modalId] = modalDiv;

                    // Asignar el evento de cierre al botón de la modal creada dinámicamente
                    const closeButton = modalDiv.querySelector('.close-button');
                    closeButton.addEventListener('click', function() {
                        modalDiv.style.display = 'none';
                    });

                    modalDiv.style.display = 'block';
                } else {
                    modalsCursos[modalId].style.display = 'block';
                }
            });
        }
    });

    window.addEventListener('click', function(event) {
        for (const modalId in modalsCursos) {
            if (event.target === modalsCursos[modalId]) {
                modalsCursos[modalId].style.display = 'none';
            }
        }
    });
});