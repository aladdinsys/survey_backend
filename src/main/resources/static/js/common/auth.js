class Auth {

    constructor() {
        this.api = new ApiClient();
    }

    async signIn(userId, password) {
        return this.api.post("/auth/sign-in", {userId, password})
            .then((res) => {
                if(res.status === 'OK') {
                    this.setToken(res);
                } else {
                    console.error('res', res);
                    throw Error('인증 실패');
                }
            });
    }

    setToken(res) {
        document.cookie = `refresh=${res.result["refreshToken"]}; max-age=86400000};`;
        this.api.token = res.result["accessToken"];
    }

}