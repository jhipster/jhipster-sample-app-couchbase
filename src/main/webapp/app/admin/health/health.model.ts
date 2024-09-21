export type HealthStatus = 'UP' | 'DOWN' | 'UNKNOWN' | 'OUT_OF_SERVICE';

export type HealthKey = 'binders' | 'diskSpace' | 'mail' | 'ping' | 'livenessState' | 'readinessState' | 'couchbase';

export interface Health {
  status: HealthStatus;
  components: {
    [key in HealthKey]?: HealthDetails;
  };
}

export interface HealthDetails {
  status: HealthStatus;
  details?: Record<string, unknown>;
}
