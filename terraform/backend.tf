terraform {

  backend "s3" {

    bucket         = "taskmaster-terraform-state-434342916666"

    key            = "dev/terraform.tfstate"

    region         = "ap-south-1"

    dynamodb_table = "terraform-locks"

    encrypt        = true

  }

}
