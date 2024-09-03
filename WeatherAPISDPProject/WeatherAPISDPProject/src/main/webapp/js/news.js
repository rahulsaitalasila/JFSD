 const API_KEY = "499d03534f224e8890dcd1f95376001c";
    const url = "https://newsapi.org/v2/everything?q=";

    async function fetchData(query) {
      const res = await fetch(`${url}${query}&apiKey=${API_KEY}`);
      const data = await res.json();
      return data;
    }

    fetchData("weather").then((data) => renderMain(data.articles));

    // render news function
    function renderMain(arr) {
      let mainHTML = '';
      for (let i = 0; i < arr.length; i++) {
        const article = arr[i];
        if (article.urlToImage && (article.title.includes("weather") || article.description.includes("weather"))) {
          mainHTML += `
            <div class="card">
              <a href=${article.url}>
                <img src=${article.urlToImage} alt=${article.title} />
                <h4>${article.title}</h4>
                <div class="publishbyDate">
                  <p>${article.source.name}</p>
                  <span>•</span>
                  <p>${new Date(article.publishedAt).toLocaleDateString()}</p>
                </div>
                <div class="desc">
                  ${article.description}
                </div>
              </a>
            </div>
          `;
        }
      }

      document.querySelector("main").innerHTML = mainHTML;
    }

    const menuBtn = document.querySelector(".menuBtn");
    const mobilemenu = document.querySelector(".mobile");

    menuBtn.addEventListener("click", () => {
      mobilemenu.classList.toggle("hidden");
    });

    const searchBtn = document.getElementById("searchForm");
    const searchBtnMobile = document.getElementById("searchFormMobile");
    const searchInputMobile = document.getElementById("searchInputMobile");
    const searchInput = document.getElementById("searchInput");

    searchBtn.addEventListener("submit", async (e) => {
      e.preventDefault();
      const data = await fetchData(searchInput.value);
      renderMain(data.articles);
    });

    searchBtnMobile.addEventListener("submit", async (e) => {
      e.preventDefault();
      const data = await fetchData(searchInputMobile.value);
      renderMain(data.articles);
    });