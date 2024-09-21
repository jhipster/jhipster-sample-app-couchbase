import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'f68dbfbe-7ae4-4303-91e2-a6fdbe18f8d5',
};

export const sampleWithPartialData: IAuthority = {
  name: 'aa0fc19c-1d29-47de-9eff-170c37105e7c',
};

export const sampleWithFullData: IAuthority = {
  name: 'dd781520-75c4-43ee-93a6-24232868ef4f',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
