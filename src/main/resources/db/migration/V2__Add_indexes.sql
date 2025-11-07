-- Performance indexes
CREATE INDEX idx_shipments_tracking_number ON shipments(tracking_number);
CREATE INDEX idx_shipments_status ON shipments(status);
CREATE INDEX idx_shipments_driver_id ON shipments(assigned_driver_id);
CREATE INDEX idx_shipments_created_at ON shipments(created_at);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_audit_log_entity ON audit_log(entity_type, entity_id);
CREATE INDEX idx_audit_log_created_at ON audit_log(created_at);