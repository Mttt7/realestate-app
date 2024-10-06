export class RegisterRequestPayload {
    constructor(public username: string,
        public password: string, public passwordRepeated: string, public firstName: string, public lastName: string) {
    }
}