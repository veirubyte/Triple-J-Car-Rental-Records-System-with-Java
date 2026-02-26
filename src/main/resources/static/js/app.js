// ============================================================
//  TRIPLE-J CAR RENTALS — Frontend API Client & Utilities
// ============================================================

// 🔴 BEFORE DEPLOYING TO VERCEL: 
// Change this to your Render backend URL (e.g., 'https://your-api.onrender.com')
const BASE_URL = '';  // Leave empty for local development

// ── API Client ──
const api = {
    async request(method, url, body) {
        const options = {
            method,
            headers: { 'Content-Type': 'application/json' },
        };
        if (body) options.body = JSON.stringify(body);

        const response = await fetch(BASE_URL + url, options);
        return response.json();
    },

    // Auth
    login(customerId) {
        return this.request('POST', '/api/auth/login', { customerId });
    },

    register(name, contactNumber) {
        return this.request('POST', '/api/auth/register', { name, contactNumber });
    },

    // Cars
    getAllCars() {
        return this.request('GET', '/api/cars');
    },

    getAvailableCars() {
        return this.request('GET', '/api/cars/available');
    },

    getCarById(id) {
        return this.request('GET', '/api/cars/' + id);
    },

    getStats() {
        return this.request('GET', '/api/cars/stats');
    },

    // Transactions
    rentCar(customerId, carId) {
        return this.request('POST', '/api/transactions/rent', { customerId, carId });
    },

    reserveCar(customerId, carId) {
        return this.request('POST', '/api/transactions/reserve', { customerId, carId });
    },

    returnCar(customerId, carId) {
        return this.request('POST', '/api/transactions/return', { customerId, carId });
    },

    // Profile
    getProfile(customerId) {
        return this.request('GET', '/api/transactions/profile/' + customerId);
    }
};

// ── Auth Guard ──
function requireAuth() {
    const customer = localStorage.getItem('customer');
    if (!customer) {
        window.location.href = 'index.html';
    }
}

function logout() {
    localStorage.removeItem('customer');
    window.location.href = 'index.html';
}

// ── User Info Loader (Sidebar) ──
function loadUserInfo() {
    const customer = JSON.parse(localStorage.getItem('customer'));
    if (!customer) return;

    const avatarEls = document.querySelectorAll('#userAvatar, #profileAvatar');
    const nameEls = document.querySelectorAll('#userName, #profileName');
    const idEls = document.querySelectorAll('#userIdDisplay');

    const initial = customer.name ? customer.name.charAt(0).toUpperCase() : '?';

    avatarEls.forEach(el => el.textContent = initial);
    nameEls.forEach(el => el.textContent = customer.name || 'User');
    idEls.forEach(el => el.textContent = 'ID: ' + customer.customerID);
}

// ── Toast Notifications ──
function showToast(type, message) {
    const container = document.getElementById('toastContainer');
    if (!container) return;

    const icons = { success: '✅', error: '❌', info: 'ℹ️' };
    const toast = document.createElement('div');
    toast.className = 'toast ' + type;
    toast.innerHTML = `
        <span class="toast-icon">${icons[type] || 'ℹ️'}</span>
        <span class="toast-message">${message}</span>
    `;

    container.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateX(100px)';
        toast.style.transition = 'all 0.4s ease';
        setTimeout(() => toast.remove(), 400);
    }, 4000);
}

// ── Mobile Sidebar Toggle ──
function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    if (sidebar) {
        sidebar.classList.toggle('open');
    }
}

// Close sidebar on clicking outside (mobile)
document.addEventListener('click', function (e) {
    const sidebar = document.getElementById('sidebar');
    const toggle = document.querySelector('.mobile-toggle');
    if (sidebar && sidebar.classList.contains('open') &&
        !sidebar.contains(e.target) &&
        (!toggle || !toggle.contains(e.target))) {
        sidebar.classList.remove('open');
    }
});
