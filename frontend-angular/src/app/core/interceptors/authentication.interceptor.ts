import { HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable, Injector } from "@angular/core";
import { AuthenticationService } from "../services/authentication/authentication.service";
import { Observable, of, switchMap } from "rxjs";
    
@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
    constructor(
        private injector: Injector
    ) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const isAuthReq = req.url.includes('/auth');
        if (isAuthReq) {
            return next.handle(req);
        }
        const authService = this.injector.get(AuthenticationService);
        const accessToken = authService.getAccessToken();
        let authReq = req;
        if (accessToken) {
            authReq = this.addAccessToken(req, accessToken);
        }
        return next.handle(authReq);
    }

    private addAccessToken(request: HttpRequest<any>, token: string): HttpRequest<any> {
        return request.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
    }

    // private handleUnauthenticated(
    //     request: HttpRequest<any>,
    //     next: HttpHandler
    // ): Observable<HttpEvent<any>> {
    //     if (!this.is)
    // }

}