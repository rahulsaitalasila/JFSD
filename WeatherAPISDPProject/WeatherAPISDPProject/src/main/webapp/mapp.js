const apiKey = '6d83156e4e40ca97d0c6924b832fe00c'; // Replace with your weather API key
let map;

document.getElementById('search-button').addEventListener('click', function () {
    const city = document.getElementById('city-input').value;
    fetchWeatherData(city);
});

function fetchWeatherData(city) {
    const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            displayWeatherData(data);
            if (map) {
                map.setView([data.coord.lat, data.coord.lon], 13);
                placeMarker([data.coord.lat, data.coord.lon], data.name);
            }
        })
        .catch(error => {
            console.error('Error fetching weather data:', error);
        });
}

function displayWeatherData(data) {
    document.getElementById('location').textContent = data.name + ', ' + data.sys.country;
    document.getElementById('temperature').textContent = data.main.temp;
    document.getElementById('humidity').textContent = data.main.humidity;
    document.getElementById('wind-speed').textContent = data.wind.speed;
    document.getElementById('weather').textContent = data.weather[0].description;
}

function placeMarker(coordinates, cityName) {
    // Clear previous markers
    if (map) {
        map.eachLayer(function (layer) {
            if (layer instanceof L.Marker) {
                map.removeLayer(layer);
            }
        });
        const marker = L.marker(coordinates).addTo(map);
        marker.bindPopup(cityName).openPopup();
    }
}

// Initialize the map
map = L.map('map').setView([-1, 2], 5);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);

// Handle map click events to fetch weather data and display markers
map.on('click', function (e) {
    const coordinates = e.latlng;
    fetchWeatherDataForCoordinates(coordinates);
});

function fetchWeatherDataForCoordinates(coordinates) {
    // Reverse geocoding to get the city or location name
    fetch(`https://nominatim.openstreetmap.org/reverse?format=json&lat=${coordinates.lat}&lon=${coordinates.lng}`)
        .then(response => response.json())
        .then(data => {
            const city = data.address.city || data.address.town || data.address.village || data.address.hamlet;
            if (city) {
                // Fetch weather data for the obtained city or location name
                fetchWeatherData(city);
                // Create a custom map marker with a weather popup
                const weatherPopup = `
                    <strong>Weather:</strong> ${document.getElementById('weather').textContent}<br>
                    <strong>Temperature:</strong> ${document.getElementById('temperature').textContent}°C<br>
                    <strong>Humidity:</strong> ${document.getElementById('humidity').textContent}%<br>
                    <strong>Wind Speed:</strong> ${document.getElementById('wind-speed').textContent} km/h
                `;
                const marker = L.marker(coordinates).addTo(map);
                marker.bindPopup(weatherPopup).openPopup();
            } else {
                console.error('City not found for the clicked coordinates.');
            }
        })
        .catch(error => {
            console.error('Error fetching city name:', error);
        });
}
