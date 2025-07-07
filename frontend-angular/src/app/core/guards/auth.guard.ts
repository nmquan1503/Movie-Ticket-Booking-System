import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { AuthenticationService } from "../services/authentication/authentication.service";
import { map, Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
})
export class AuthGuard implements CanActivate {
    constructor(
        private authService: AuthenticationService,
        private router: Router
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> {
        return this.authService.isLogined$.pipe(
            map((isLogin) => {
                if (isLogin) {
                    return true;
                }
                else {
                    return this.router.createUrlTree(['/login'], {
                        queryParams: { redirectTo: state.url },
                    });
                }
            })
        );
    }
}