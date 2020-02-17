package us.gonet.serverutils.model.jdb.lite;

import us.gonet.serverutils.model.jdb.Device;

 public class DeviceStatusLite {

        private int id;
        private String atm;
        private String status;
        private Device device;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAtm() {
            return this.atm;
        }

        public void setAtm(String atm) {
            this.atm = atm;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Device getDevice() {
            return this.device;
        }

        public void setDevice(Device device) {
            this.device = device;
        }

        public String toString() {
            return "DeviceStatus{id=" + this.id + ", atm=" + this.atm + ", status='" + this.status + '\'' + ", device=" + this.device + '}';
        }
}
