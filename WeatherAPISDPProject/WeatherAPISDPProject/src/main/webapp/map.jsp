<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('../images/dark-1836972_1280.jpg');
            height: 100vh;
            justify-content: space-between; /* Align items to the left and right */
        }

        #container {
            display: flex;
            justify-content: space-between;
            padding: 20px;
        }

        #weather-container {
            max-width: 600px;
        }

        #map-container {
            flex-grow: 1;
            margin-left: 20px;
        }

        #search-button {
            background-color: black;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }

        #city-input {
            width: 100%;
            padding: 8px;
            margin-bottom: 8px;
        }

        #current-weather {
            border: 1px solid #ddd;
            padding: 16px;
            background-color: #f9f9f9;
            margin-top: 20px;
        }

        #map {
            height: 600px;
            margin-top: 20px;
        }
    </style>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css">
</head>
<body>
<%@ include file="usernavbar.jsp" %>
    <div id="container">
        <div id="weather-container">
            <input type="text" id="city-input" placeholder="Enter city or location">
            <button id="search-button">Search</button>
            <div id="current-weather">
                <h2>Current Weather</h2>
                <p>Location: <span id="location"></span></p>
                <p>Temperature: <span id="temperature"></span>°C</p>
                <p>Humidity: <span id="humidity"></span>%</p>
                <p>Wind Speed: <span id="wind-speed"></span> km/h</p>
                <p>Weather: <span id="weather"></span></p>
            </div>
        </div>
        <div id="map-container">
            <div id="map"></div>
        </div>
    </div>

    <!-- Add Leaflet JavaScript -->
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
    <script src="mapp.js"></script>
    <script>
        document.getElementById("search-button").addEventListener("click", function () {
            // Get weather data from the form input
            const location = document.getElementById("city-input").value;

            // Simulated data fetching (replace with actual API calls)
            const temperature = fetchTemperature(location);
            const humidity = fetchHumidity(location);
            const windSpeed = fetchWindSpeed(location);
            const weather = fetchWeather(location);

            // Create a JSON object with the data
            const weatherData = {
                location,
                temperature,
                humidity,
                windSpeed,
                weather,
            };

            // Send the data to the server
            fetch("/save-weather", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(weatherData),
            })
                .then(response => {
                    if (response.status === 200) {
                        // Weather data saved successfully
                    } else {
                        // Handle errors
                    }
                });
        });

        // Simulated data fetching functions (replace with actual logic)
        function fetchTemperature(location) {
            // Simulated temperature data
            return "25";
        }

        function fetchHumidity(location) {
            // Simulated humidity data
            return "70";
        }

        function fetchWindSpeed(location) {
            // Simulated wind speed data
            return "15";
        }

        function fetchWeather(location) {
            // Simulated weather condition data
            return "Partly Cloudy";
        }
    </script>
</body>
</html>
