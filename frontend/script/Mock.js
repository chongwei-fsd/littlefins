class Mock{

    constructor(){
        // TODO: WIP
    }
    
    // Static variables
    /* ************** */
    
    // !! Mock response 200 SUCCESS (akin to response.status === 200)
    static successResponse = {                                  
        ok: true,                                               // Indicates the response was successful
        status: 200,                                            // HTTP status code for Not Found
        statusText: 'OK',                                       // Corresponding status text
        json: async () => ({ message: 'Success!' }),            // Mocked JSON response
        text: async () => 'Success!',                           // Mocked plain text response
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    };

    // !! Mock response 404 NOT_FOUND (akin to response.status === 400)
    static notFoundResponse = {                                 
        ok: false,                                              // Indicates the response was NOT successful
        status: 404,                                            // HTTP status code for Not Found
        statusText: 'NOT_FOUND',                                // Corresponding status text
        json: async () => ({ error: 'Resource not found' }),    // Mocked JSON response
        text: async () => 'Resource not found',                 // Mocked plain text response
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    };
    // !! Mock response 403 FORBIDDEN (akin to response.status === 403)
    static forbiddenResponse = {                                 
        ok: false,                                              // Indicates the response was FORBIDDEN 
        status: 403,                                            // HTTP status code for FORBIDDEN
        statusText: 'FORBIDDEN',                                // Corresponding status text
        json: async () => ({ error: 'Forbidden' }),    // Mocked JSON response
        text: async () => 'Forbidden',                 // Mocked plain text response
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    };

    // !! Mock token containing the subscriber (sub: martin@example.com), and Unix Timestamps: issued at (iat: 1st Sept 2024) and expiry (exp: 1st Sept 2025)
    static mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImNob25nIHdlaSIsImVtYWlsIjoiY2hvbmd3ZWlAZ29vZ2xlLmNvbSIsInJvbGUiOiJDRU8iLCJpYXQiOjE3MjU1ODc0NDcsImV4cCI6MTc1NzEyMzQ0N30.re75vBnzfeVA3THXYsZTl3N18BpQlGWi0WLy7Oh6w0o";

    // Static methods
    /* ************** */

    static getMockSuccess(){                                    // Returns mock OK status
        return this.successResponse;
    }

    static getMockNotFound(){                                   // Returns mock NOT_FOUND status
        return this.successResponse;
    }
    static getMockForbidden(){                                   // Returns mock NOT_FOUND status
        return this.forbiddenResponse;
    }

    static getToken(status = false){                            // Returns mock NOT_FOUND status
        return status ? this.mockToken : status;
    }

    static fetchMockUser(){
        const users=[
            {
                "id": 1,
                "username": "nancy",
                "password": "nancy123",
                "email": "nancy@gmail.com",
                "dob": "1990-02-02",
                "coin": 0
            },
            {
                "id": 2,
                "username": "bong",
                "password": "bong123",
                "email": "bong@gmail.com",
                "dob": "1990-03-03",
                "coin": 0
            },
            {
                "id": 3,
                "username": "josh",
                "password": "josh123",
                "email": "josh@gmail.com",
                "dob": "1990-04-04",
                "coin": 0
            },
            {
                "id": 4,
                "username": "michelle",
                "password": "michelle123",
                "email": "michelle@gmail.com",
                "dob": "1990-05-05",
                "coin": 0
            }
        ]

        const userResp={
            "users":users
        }

        return new Promise((resolve)=>{
            setTimeout(()=>{
                resolve(userResp)
            },2000)
        })
    }

}