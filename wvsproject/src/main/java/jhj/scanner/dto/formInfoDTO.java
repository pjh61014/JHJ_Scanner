package jhj.scanner.dto;

import java.util.Arrays;

public class formInfoDTO {

   private String form_type;
   private String[] form_name;
   private String[] tag_id;

   public formInfoDTO() {
      super();
   }

   public formInfoDTO(String form_type, String[] form_name, String[] tag_id) {
      super();
      this.form_type = form_type;
      this.form_name = form_name;
      this.tag_id = tag_id;
   }

   public String getForm_type() {
      return form_type;
   }

   public void setForm_type(String form_type) {
      this.form_type = form_type;
   }

   public String[] getForm_name() {
      return form_name;
   }

   public void setForm_name(String[] form_name) {
      this.form_name = form_name;
   }

   public String[] gettag_id() {
      return tag_id;
   }

   public void settag_id(String[] tag_id) {
      this.tag_id = tag_id;
   }

   @Override
   public String toString() {
      return "formInfoDTO [form_type=" + form_type + ", form_name=" + Arrays.toString(form_name) + ", tag_id="
            + Arrays.toString(tag_id) + "]";
   }
   
   

}