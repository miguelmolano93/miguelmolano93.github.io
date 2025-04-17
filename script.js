document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.querySelector('.menu-toggle');
    const nav = document.querySelector('nav');
    const flechaIzquierda = document.querySelector('.flecha-izquierda');
    const flechaDerecha = document.querySelector('.flecha-derecha');
    const investigadoresContainer = document.getElementById('investigadores-container');
    const modalTriggers = document.querySelectorAll('[data-modal]');
    const closeButtons = document.querySelectorAll('.close-button');

    // Toggle Mobile Menu
    if (menuToggle && nav) {
        menuToggle.addEventListener('click', () => {
            const expanded = menuToggle.getAttribute('aria-expanded') === 'true' || false;
            menuToggle.setAttribute('aria-expanded', !expanded);
            nav.style.display = expanded ? 'none' : 'flex'; // Or 'block
// Investigator Carousel Navigation
if (investigadoresContainer && flechaIzquierda && flechaDerecha) {
    flechaIzquierda.addEventListener('click', () => {
        investigadoresContainer.scrollLeft -= 320; // Adjust scroll amount based on investigator width + margin
    });

    flechaDerecha.addEventListener('click', () => {
        investigadoresContainer.scrollLeft += 320; // Adjust scroll amount based on investigator width + margin
    });
}

// Modal Interactions
modalTriggers.forEach(trigger => {
    trigger.addEventListener('click', (event) => {
        event.preventDefault();
        const modalId = trigger.getAttribute('data-modal');
        const modal = document.getElementById(modalId);
        if (modal) {
            modal.style.display = 'block';
        }
    });

    // Also handle keyboard accessibility for elements acting as buttons
    trigger.addEventListener('keydown', (event) => {
        if (event.key === 'Enter' || event.key === ' ') {
            event.preventDefault();
            const modalId = trigger.getAttribute('data-modal');
            const modal = document.getElementById(modalId);
            if (modal) {
                modal.style.display = 'block';
            }
        }
    });
});

closeButtons.forEach(button => {
    button.addEventListener('click', () => {
        const modal = button.closest('.modal');
        if (modal) {
            modal.style.display = 'none';
        }
    });
});

window.addEventListener('click', (event) => {
    if (event.target.classList.contains('modal')) {
        event.target.style.display = 'none';
    }
});

// Basic keyboard navigation for modals
document.addEventListener('keydown', (event) => {
    if (event.key === 'Escape') {
        const openModals = document.querySelectorAll('.modal[style*="display: block;"]');
        openModals.forEach(modal => {
            modal.style.display = 'none';
        });
    }
});