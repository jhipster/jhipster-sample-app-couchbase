import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '4c7e1d0e-bbec-40d2-8c0e-fdbf17cc3b01',
};

export const sampleWithPartialData: IAuthority = {
  name: '05952638-2b65-4456-a7ee-b9c5507fe5d6',
};

export const sampleWithFullData: IAuthority = {
  name: 'b880150d-288e-41a8-8461-2474e698ba4f',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
