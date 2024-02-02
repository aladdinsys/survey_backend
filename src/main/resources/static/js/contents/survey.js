'use strict';

class SurveyRequest {
    id;
    title;
    description;
    content;

    constructor(survey) {

        const {
            title,
            description,
            content
        } = survey;

        this.title = title;
        this.description = description;
        this.content = content;
    }
}

class SurveyResponse {

    id;
    title;
    description;
    content;
    publishId;
    owner;
    createdAt;
    updatedAt;
    publishedAt;

    constructor(result = {}) {
        const {
            id,
            title,
            description,
            content,
            publishId,
            owner,
            createdAt,
            updatedAt,
            publishedAt
        } = result;

        this.id = id;
        this.title = title;
        this.description = description;
        this.content =  content;
        this.publishId =  publishId;
        this.owner =  owner;
        this.createdAt =  createdAt;
        this.updatedAt =  updatedAt;
        this.publishedAt =  publishedAt;
    }

}

const surveyService = {

    api: new ApiClient(),

    getSurvey: async (id) => {
        return surveyService.api.get(`/api/surveys/${id}`)
            .then((res) => res.result);
    },

    findOwns: async () => {
        return surveyService.api.get(`/api/surveys/find-own`)
            .then((res) => res.result.map(new SurveyResponse));
    },

    postSurvey: async (surveyRequest) => {
        const path = surveyRequest.id ? `/api/surveys/${surveyRequest.id}` : `/api/surveys`;
        return surveyService.api.post(path, surveyRequest)
            .then((res) => res.result);
    },

    patchSurvey: async (id, surveyRequest) => {
        return surveyService.api.patch(`/api/surveys/${id}`, surveyRequest)
            .then((res) => res.result);
    },

    publishSurvey: async (id) => {
        return surveyService.api.patch(`/api/surveys/${id}/publish`)
            .then((res) => res.result);
    },

    publishSurveyAndSave: async (surveyRequest) => {
        return surveyService.api.patch(`/api/surveys/publish`, surveyRequest)
            .then((res) => res.result);
    },
}

const setEventListener = () => {
    const publishButton = document.getElementById('btnPublish');
    publishButton.addEventListener("click", (event) => {

        if(globalThis.aladdinSurvey.id) {
            const { id } = globalThis.aladdinSurvey.id;

            surveyService.publishSurvey(id).then((res) => {
                globalThis.aladdinSurvey.id = res.id;
            });

            return;
        }

        const {
            title,
            description,
            sections
        } = globalThis.aladdinSurvey.getSurvey()

        const content = JSON.stringify(sections);

        const request = new SurveyRequest({title, description, content});

        surveyService.publishSurveyAndSave(request).then((res) => {
            globalThis.aladdinSurvey.id = res.id;
        });

    }, false);

    const saveButton = document.getElementById('btnSave');
    saveButton.addEventListener("click", (event) => {

        const {
            title,
            description,
            sections
        } = globalThis.aladdinSurvey.getSurvey()

        const content = JSON.stringify(sections);
        const request = new SurveyRequest({title, description, content});

        if(globalThis.aladdinSurvey.id) {
            const { id } = globalThis.aladdinSurvey;
            surveyService.patchSurvey(id, request).then((res) => {
                console.log(res);
            });
        } else {
            surveyService.postSurvey(request).then((res) => {
                globalThis.aladdinSurvey.id = res.id;
            });
        }
    }, false);
}

setEventListener();

const initialize = () => {
    globalThis.aladdinSurvey = new AladdinSurvey({
        target: document.querySelector('survey')
    });
}

initialize();

const auth = new Auth();
auth.signIn("test", "test123").then(((res) => {
    console.log(res);
}));
