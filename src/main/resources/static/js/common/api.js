class ApiClient {

    constructor(baseUrl = "") {
        if (ApiClient.instance) {
            return ApiClient.instance;
        }

        this._baseUrl = baseUrl;
        this._token = null;

        ApiClient.instance = this;
    }

    set baseurl(url) {
        this._baseUrl = url;
    }

    set token(token) {
        this._token = token;
    }

    get headers() {
        const headers = {
            'Content-Type': 'application/json',
        };

        if (this._token) {
            headers['Authorization'] = `Bearer ${this._token}`;
        }

        return headers;
    }

    async onSuccess(response) {
        return response.json();
    }

    async onFailure(error) {
        return error.json();
    }

    setToken(res) {
        document.cookie = `refresh=${res.result["refreshToken"]}; max-age=86400000};`;
        this._token = res.result["accessToken"];
    }

    retrieveTokenFromCookie(name = "refresh") {
        const cookieValue = document.cookie.split('; ').find(row => row.startsWith(name)).split('=')[1];
        return cookieValue || null;
    }

    async refreshToken() {
        this._token = this.retrieveTokenFromCookie('refresh');
        const response = await fetch(`${this._baseUrl}/auth/refresh-token`, {
            method: 'POST',
            headers: this.headers,
        });

        return response.json();
    }

    async retryOriginalRequest(url, originalMethod, originalBody) {
        const response = await fetch(url, {
            method: originalMethod,
            headers: this.headers,
            body: originalBody,
        });

        return this.handleUnauthorized(response, originalMethod, originalBody);
    }

    async call(path, method, data) {
        const response = await fetch(path, {
            method: method,
            headers: this.headers,
            body: JSON.stringify(data),
        });

        return this.handleUnauthorized(response, method, data);
    }

    async handleUnauthorized(response, method, data) {
        if (response.status === 401) {
            this.refreshToken().then((res) => {
                    this.setToken(res);

                    return this.retryOriginalRequest(response.url, method, data);
                }
            );
        }

        return response.json();
    }

    async get(endpoint) {
        return this.call(`${this._baseUrl}${endpoint}`, "GET");
    }

    async post(endpoint, data) {
        return this.call(`${this._baseUrl}${endpoint}`, "POST", data);
    }

    async put(endpoint, data) {
        return this.call(`${this._baseUrl}${endpoint}`, "PUT", data);
    }

    async patch(endpoint, data) {
        return this.call(`${this._baseUrl}${endpoint}`, "PATCH", data);
    }

    async delete(endpoint) {
        return this.call(`${this._baseUrl}${endpoint}`, "DELETE");
    }
}