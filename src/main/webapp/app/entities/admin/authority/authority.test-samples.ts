import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'f36f8bd5-bcf3-4b0e-8b76-afe3443c043d',
};

export const sampleWithPartialData: IAuthority = {
  name: 'deb2caf6-af4d-49bd-8e31-a87fb82d15b6',
};

export const sampleWithFullData: IAuthority = {
  name: 'a2a40eff-ca19-495c-b21e-d3209c7bdce2',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
