import { Inject, Injectable, PLATFORM_ID } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
class WindowService {
    
    get nativeWindow(): Window | null {
        return typeof window !== 'undefined' ? window : null;
    }

    requestAnimationFrame(callback: () => void): any {
        
        const win = this.nativeWindow;
        if (win) {
            return win.requestAnimationFrame(callback);
            
        }
        else {
            setTimeout(callback, 16);
        }
    }

    setInterval(callback: () => void, timeout: number): number | null {
        const win = this.nativeWindow;
        if (win) {
            return win.setInterval(callback, timeout);
        }
        else {
            return null;
        }
    }

    clearInterval(timerId: number | null): void {
        const win = this.nativeWindow;
        if (win && timerId !== null) {
            win.clearInterval(timerId);
        }
    }

    addEventListener<K extends keyof WindowEventMap>(
        type: K,
        listener: (this: Window, ev: WindowEventMap[K]) => any,
        options?: boolean | AddEventListenerOptions
    ): void {
        const win = this.nativeWindow;
        if (win) {
            win.addEventListener(type, listener, options);
        }
    }

    removeEventListener<K extends keyof WindowEventMap>(
        type: K,
        listener: (this: Window, ev: WindowEventMap[K]) => any,
        options?: boolean | EventListenerOptions
    ): void {
        const win = this.nativeWindow;
        if (win) {
            win.removeEventListener(type, listener, options);
        }
    }
    
    redirect(url: string): void {
        const win = this.nativeWindow;
        if (win) {
            win.location.href = url;
        }
    }

    scrollToTop(): void {
        const win = this.nativeWindow;
        if (win) {
            win.scrollTo({top: 0});
        }
    }
}

export { WindowService };