provider "aws" {
  region = var.aws_region

  default_tags {
    tags = {
      Project     = "TaskMaster"
      Environment = var.environment
      ManagedBy   = "Terraform"
      Owner       = "Shubhek Sam"
    }
  }
}
