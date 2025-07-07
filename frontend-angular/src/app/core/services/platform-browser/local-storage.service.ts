import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class LocalStorageService {
    get nativeLocalStorage(): Storage | null {
        return typeof localStorage !== 'undefined' ? localStorage : null;
    }

    getItem(key: string): string | null {
        const storage = this.nativeLocalStorage;
        return storage ? storage.getItem(key) : null;
    }

    setItem(key: string, value: string): void {
        const storage = this.nativeLocalStorage;
        if (storage) {
            storage.setItem(key, value);
        }
    }

    removeItem(key: string): void {
        const storage = this.nativeLocalStorage;
        if (storage) {
            storage.removeItem(key);
        }
    }

    clear(): void {
        const storage = this.nativeLocalStorage;
        if (storage) {
            storage.clear();
        }
    }
}