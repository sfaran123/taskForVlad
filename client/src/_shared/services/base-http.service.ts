import { Injectable } from '@angular/core';
import { HttpHeaders, HttpParams } from '@angular/common/http';


import { environment } from '../../../src/environment';


@Injectable()
export abstract class BaseHttpService {

  readonly apiUrl = environment.apiUrl;

  protected constructor() {}


}
