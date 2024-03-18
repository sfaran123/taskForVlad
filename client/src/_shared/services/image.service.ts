import { Injectable } from "@angular/core";
import { BaseHttpService } from "./base-http.service";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class ImageService extends BaseHttpService {
    readonly endPoint = this.apiUrl + 'image';
    constructor(private http: HttpClient){
        super();
    }

    getImages(): Observable<string[]> {
        return this.http.get<string[]>(this.apiUrl + "/all");
      }

    deleteImage(imageUrl: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${encodeURIComponent(imageUrl)}`);
  }
}