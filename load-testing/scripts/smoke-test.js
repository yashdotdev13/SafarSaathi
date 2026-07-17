import http from 'k6/http';
import { check } from 'k6';

const BASE_URL = __ENV.BASE_URL;

export const options = {
    vus: 1,
    duration: '30s',
};

export default function () {

    const response = http.get(
        `${BASE_URL}/actuator/health`
    );

    check(response, {
        'status is 200': (r) => r.status === 200,
    });
}