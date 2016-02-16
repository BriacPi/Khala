export interface UserInfo {
    constructor(
        email: string,
        firstName: string,
        lastName: string,
        headline: string,
    //By defaut point to the anonymous head.
        urlPhoto: string,
        urlUserInfo: string,
        urlPaymentInfo: string) { }

}


