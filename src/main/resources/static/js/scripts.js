document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.querySelector('.menu-toggle');
    const mobileMenuContainer = document.querySelector('.mobile-menu-container');

    if (menuToggle && mobileMenuContainer) {
        menuToggle.addEventListener('click', function() {
            this.classList.toggle('active');
            mobileMenuContainer.classList.toggle('active');
        });

        document.querySelectorAll('.mobile-menu a').forEach(link => {
            link.addEventListener('click', () => {
                menuToggle.classList.remove('active');
                mobileMenuContainer.classList.remove('active');
            });
        });
    }

    const slides = document.querySelectorAll('.slide');
    const indicators = document.querySelectorAll('.indicator');
    const prevBtn = document.querySelector('.slider-btn.prev');
    const nextBtn = document.querySelector('.slider-btn.next');
    let currentSlide = 0;

    function showSlide(index) {
        slides.forEach(slide => slide.classList.remove('active'));
        indicators.forEach(indicator => indicator.classList.remove('active'));

        slides[index].classList.add('active');
        indicators[index].classList.add('active');
        currentSlide = index;
    }

    function nextSlide() {
        let nextIndex = (currentSlide + 1) % slides.length;
        showSlide(nextIndex);
    }

    function prevSlide() {
        let prevIndex = (currentSlide - 1 + slides.length) % slides.length;
        showSlide(prevIndex);
    }

    let slideInterval = setInterval(nextSlide, 5000);

    if (nextBtn) {
        nextBtn.addEventListener('click', () => {
            clearInterval(slideInterval);
            nextSlide();
            slideInterval = setInterval(nextSlide, 5000);
        });
    }

    if (prevBtn) {
        prevBtn.addEventListener('click', () => {
            clearInterval(slideInterval);
            prevSlide();
            slideInterval = setInterval(nextSlide, 5000);
        });
    }

    if (indicators.length > 0) {
        indicators.forEach((indicator, index) => {
            indicator.addEventListener('click', () => {
                clearInterval(slideInterval);
                showSlide(index);
                slideInterval = setInterval(nextSlide, 5000);
            });
        });
    }
});