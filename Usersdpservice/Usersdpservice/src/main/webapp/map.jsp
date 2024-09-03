<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="css/style.css">
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
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"><img src="images/download.png" alt="logo" style="height: 50px; width: 100px;"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="userhome">HOME</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="weather">WEATHER</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link active" aria-current="page" href="map">WEATHER MAP</a>
                    </li>
                    <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="news">NEWS</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="weatherdb">WEATHER DATA</a>
              </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="contact">CONTACT</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="updateprofile">UPDATE PROFILE</a>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-but">
                        <a class="btn btn-outline-light" href="userlogin">LOGOUT</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

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
    <script src="js/mapp.js"></script>
</body>
</html>
