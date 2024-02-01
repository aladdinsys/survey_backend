(() => {

    const setEventListener = () => {
        const publishButton = document.getElementById('btnPublish');

        publishButton.addEventListener((event) => {

        });
    }


    const addSurvey = () => {

        // 초기화 및 마운트를 위한 코드
        const targetElement = document.querySelector('survey');

        return new AladdinSurvey({
            target: targetElement,
            props: {
                "message": "테스트2"
            }
        });
    };

    addSurvey();
})();
