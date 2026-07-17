import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    scenarios: {
        login_load: {
            executor: 'constant-vus',
            vus: 20,
            duration: '30s',
        },
    },

    thresholds: {
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<500'],
    },
};

const BASE_URL = 'http://host.docker.internal:8081';

export default function () {

    const payload = JSON.stringify({
        email: 'abc@example.com',
        password: 'rajuq1234'
    });

    const params = {
        headers: {
            'Content-Type': 'application/json'
        }
    };

    const res = http.post(
        `${BASE_URL}/auth/login`,
        payload,
        params
    );

    check(res, {
        'status is 200': (r) => r.status === 200,
        'contains accessToken': (r) =>
            JSON.parse(r.body).accessToken !== undefined,
    });

    sleep(1);
}